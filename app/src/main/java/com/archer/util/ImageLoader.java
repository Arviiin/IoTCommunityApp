package com.archer.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;
import android.widget.ImageView;

import com.archer.adapter.ForumAdapter;
import com.archer.start.R;
import com.archer.widget.LoadListView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Acher on 2015/8/12.
 */
public class ImageLoader {
    private ImageView mIcon;
    private LruCache<String, Bitmap> mCaches;
    private String mUrl;
    private LoadListView mListView;
    private Set<NewsAsyncTask> mTask;

    public ImageLoader(LoadListView listView) {
        mListView = listView;
        mTask = new HashSet<>();
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 4;
        mCaches = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //在每次存入缓存调用大神
                return value.getByteCount();
            }
        };
    }

    //增加到缓存
    public void addbitmapToCache(String url, Bitmap bitmap) {
        if (getBitmapFromCache(url) == null) {
            mCaches.put(url, bitmap);
        }
    }


    //从缓存中获取数据
    public Bitmap getBitmapFromCache(String url) {
        return mCaches.get(url);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mIcon.getTag().equals(mUrl)) {
                mIcon.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };

    public void loadImages(int start, int end) {
        for (int i = start; i < end; i++) {
            String url = ForumAdapter.URLS[i];
            Bitmap bitmap = getBitmapFromCache(url);
            if (bitmap == null) {
                NewsAsyncTask task = new NewsAsyncTask(url);
                task.execute(url);
                mTask.add(task);
            } else {
                ImageView imageView = (ImageView) mListView.findViewWithTag(url);
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    //使用多线程的方式加载图片
    public void showImageByThread(ImageView imageView, final String url) {
        Bitmap bitmap = getBitmapFromCache(url);

        mIcon = imageView;
        mUrl = url;
        if (bitmap == null) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    Bitmap bitmap = getBitmapFromURL(url);
                    Message message = Message.obtain();
                    message.obj = bitmap;
                    handler.sendMessage(message);
                }
            }.start();
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    public Bitmap getBitmapFromURL(String urlString) {
        Bitmap bitmap;
        InputStream is = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            if (bitmap != null) {
                addbitmapToCache(urlString, bitmap);
            }
            connection.disconnect();
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;

    }

    //使用AsyncTask加载图片
    public void showImageByAsyncTask(ImageView imageView, String url) {
        Bitmap bitmap = getBitmapFromCache(url);
        if (bitmap == null) {
            imageView.setImageResource(R.mipmap.ic_launcher);

        } else {
            imageView.setImageBitmap(bitmap);
        }

    }

    public void cancelAllTask() {
        if (mTask != null) {
            for (NewsAsyncTask task : mTask) {
                task.cancel(false);
            }
        }
    }

    private class NewsAsyncTask extends AsyncTask<String, Void, Bitmap> {
        //  private ImageView mImageView;
        private String mUrl;

        public NewsAsyncTask(String url) {
            //   mImageView=img;
            mUrl = url;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            return getBitmapFromURL(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ImageView imageView = (ImageView) mListView.findViewWithTag(mUrl);
            if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
            mTask.remove(this);
        }
    }

}
