package com.zongbutech.iparty.view.activity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zongbutech.iparty.R;

import java.io.File;

public abstract class CameraActivity extends BaseActivity {

    public Context ct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ct = this;


    }


    //    照相
    public static final int CAMERA_REQUEST = 1888;
    public final int PICTURE_ASK = 1001;
    //图片编辑之后
    public static final int ACTION_REQUEST_EDITIMAGE = 9;
    //得到的图像
    public Uri mUri;

    public void showImageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ct);
        builder.setTitle("选择头像");
        builder.setPositiveButton("相机", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                String status = Environment.getExternalStorageState();
                if (status.equals(Environment.MEDIA_MOUNTED)) {
                    try {
                        String saveDir = Environment.getExternalStorageDirectory() + "/lpj/";
                        File dir = new File(saveDir);
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        String newName = System.currentTimeMillis() + ".jpg";
                        File file = new File(saveDir, newName);
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        mUri = Uri.fromFile(file);
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("相册", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent pictureSelect = new Intent(Intent.ACTION_GET_CONTENT);
                pictureSelect.setType("image/*");
                //调用系统相册
                startActivityForResult(pictureSelect, PICTURE_ASK);
                dialog.dismiss();
            }
        });
        builder.create().show();
    }


    private void GitBitmap(final Uri originalUri) {
        GitBitmap(originalUri.toString());
    }

    private void GitBitmap(final String originalUri) {
        Glide.with(ct).load(originalUri).placeholder(R.drawable.ic_image_loading).error(R.drawable.ic_image_loadfail).crossFade().into(new SimpleTarget<GlideDrawable>(width, width) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                Bitmap bitmap = ((GlideBitmapDrawable) resource).getBitmap();
                bitmap.getWidth();
                Float xMatrix = (Float.valueOf(width)) / bitmap.getWidth();
                Matrix matrix = new Matrix();
                matrix.postScale(xMatrix, xMatrix); //长和宽放大缩小的比例
                Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

                String result = originalUri;
                if (result.startsWith("file://")) {
                    result = result.replaceFirst("file://", "");
                }
                setImage(result, resizeBmp);
            }
        });
    }


    public  abstract void setImage(String originalUri, Bitmap resizeBmp);


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == ACTION_REQUEST_EDITIMAGE) {
                String newFilePath = data.getStringExtra("save_file_path");
                GitBitmap(Uri.fromFile(new File(newFilePath)));
            } else {
                if (data != null) {
                    if (requestCode == PICTURE_ASK) {
                        Uri originalUri = data.getData();
                        String[] proj = {MediaStore.Images.Media.DATA};
                        //好像是android多媒体数据库的封装接口，具体的看Android文档
                        Cursor cursor = managedQuery(originalUri, proj, null, null, null);
                        //按我个人理解 这个是获得用户选择的图片的索引值
                        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        //将光标移至开头 ，这个很重要，不小心很容易引起越界
                        cursor.moveToFirst();
                        //最后根据索引值获取图片路径
                        String path = cursor.getString(column_index);
                        if (path.startsWith("file://")) {
                            path = path.replaceFirst("file://", "");
                        }
                        GitBitmap(path);

                    } else if (requestCode == CAMERA_REQUEST) {
                        Uri mUri = data.getData();
                        if (mUri != null) {
                            GitBitmap(mUri);
                        } else {
                            Bitmap photo = (Bitmap) data.getExtras().get("data");
                            if (photo != null) {
                                setImage("", photo);
                            }
                        }
                    }
                } else {
                    if (requestCode == CAMERA_REQUEST) {
                        GitBitmap(mUri);
                    }
                }
            }
        }
    }

}
