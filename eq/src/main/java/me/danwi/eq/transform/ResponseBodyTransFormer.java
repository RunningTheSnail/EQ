package me.danwi.eq.transform;

import io.reactivex.ObservableTransformer;
import me.danwi.eq.entity.DownLoadResult;
import okhttp3.ResponseBody;

/**
 * Created by RunningSnail on 16/7/21.
 */
public abstract class ResponseBodyTransFormer implements ObservableTransformer<ResponseBody, DownLoadResult> {
    public static final String TAG = "ResponseBodyTransFormer";

//    @Override
//    public Observable<DownLoadResult> call(Observable<ResponseBody> responseBodyObservable) {
//
//        return responseBodyObservable
//                .flatMap(new Func1<ResponseBody, Observable<DownLoadResult>>() {
//                    @Override
//                    public Observable<DownLoadResult> call(final ResponseBody responseBody) {
//                        return Observable.create(new Observable.OnSubscribe<DownLoadResult>() {
//                            @Override
//                            public void call(Subscriber<? super DownLoadResult> subscriber) {
//                                InputStream inputStream = responseBody.byteStream();
//                                DownLoadResult downLoadResult = new DownLoadResult();
//
//                                //判断
//                                boolean sdCardExist = SdCardUtils.isExist();
//
//                                String folderPath = "";
//                                if (sdCardExist) {
//                                    //文件夹
//                                    folderPath = SdCardUtils.getRootPath() + File.separator + getFolder() + File.separator;
//                                }
//
//                                File fileDir = new File(folderPath);
//                                //创建文件夹
//                                if (!fileDir.exists()) {
//                                    fileDir.mkdirs();
//                                }
//                                File file = new File(folderPath + getFileName());
//                                //输出流
//                                FileOutputStream out = null;
//                                try {
//                                    out = new FileOutputStream(file);
//                                } catch (FileNotFoundException e) {
//                                    e.printStackTrace();
//                                }
//
//                                //缓冲区
//                                byte[] buffer = new byte[1024];
//                                //文件总体大小
//                                downLoadResult.contentLength = responseBody.contentLength();
//                                //判断是否下载完成
//                                downLoadResult.done = false;
//                                long current = 0;
//                                long temp;
//                                try {
//                                    //读取流
//                                    while ((temp = inputStream.read(buffer)) != -1) {
//                                        //获取当前读取的字节数
//                                        current = current + temp;
//                                        downLoadResult.current = current;
//                                        //已经下载的进度比
//                                        downLoadResult.progress = (current * 100 / downLoadResult.contentLength);
//                                        if (current == downLoadResult.contentLength) {
//                                            downLoadResult.done = true;
//                                        }
//                                        if (out != null) {
//                                            //写入文件
//                                            out.write(buffer, 0, (int) temp);
//                                            out.flush();
//                                        }
//                                        //发送数据
//                                        subscriber.onNext(downLoadResult);
//                                    }
//                                    //下载完成
//                                    subscriber.onCompleted();
//                                } catch (IOException e) {
//                                    LogUtils.e(TAG, e.toString());
//                                    subscriber.onError(e);
//                                } finally {
//                                    try {
//                                        if (out != null) {
//                                            out.close();
//                                        }
//                                    } catch (IOException e) {
//                                        LogUtils.e(TAG, e.toString());
//                                        subscriber.onError(e);
//                                    }
//
//                                    try {
//                                        inputStream.close();
//                                    } catch (IOException e) {
//                                        LogUtils.e(TAG, e.toString());
//                                        subscriber.onError(e);
//                                    }
//                                }
//                            }
//                        });
//                    }
//                })
//                .sample(1000, TimeUnit.MILLISECONDS);
//    }

    //获取文件夹
    public abstract String getFolder();

    //获取文件名
    public abstract String getFileName();
}
