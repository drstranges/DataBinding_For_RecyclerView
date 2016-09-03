/*
 *  Copyright Roman Donchenko. All Rights Reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.drextended.rvdbsample.util;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PathEffect;

import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;

/**
 * Custom Glide Transform for make circle image with colored border
 */
public class CircleBorderedTransform implements Transformation<Bitmap> {

    private final int mBorderColor;
    private BitmapPool mBitmapPool;
    private PathEffect mPathEffect = new CornerPathEffect(10);

    public CircleBorderedTransform(final BitmapPool pool, final int borderColor) {
        mBitmapPool = pool;
        mBorderColor = borderColor;
    }

    @Override
    public Resource<Bitmap> transform(Resource<Bitmap> resource, int outWidth, int outHeight) {
        Bitmap source = resource.get();
        int size = Math.min(source.getWidth(), source.getHeight());

        int width = (source.getWidth() - size) / 2;
        int height = (source.getHeight() - size) / 2;

        Bitmap bitmap = mBitmapPool.get(size, size, Bitmap.Config.ARGB_8888);
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(source, BitmapShader.TileMode.CLAMP,
                BitmapShader.TileMode.CLAMP);
        if (width != 0 || height != 0) {
            Matrix matrix = new Matrix();
            matrix.setTranslate(-width, -height);
            shader.setLocalMatrix(matrix);
        }
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float r = size / 2f;
        float stroke = getStrokeWidth(r);
        canvas.drawCircle(r, r, r, paint);

        preparePaintForCircleBorder(paint, stroke);

        canvas.drawCircle(r, r, r - stroke / 2f, paint);

        return BitmapResource.obtain(bitmap, mBitmapPool);
    }

    private void preparePaintForCircleBorder(final Paint _paint, final float _stroke) {
        _paint.setShader(null);
        _paint.setColor(mBorderColor);
        _paint.setStyle(Paint.Style.STROKE);
        _paint.setStrokeJoin(Paint.Join.ROUND);
        _paint.setStrokeCap(Paint.Cap.ROUND);
        _paint.setPathEffect(mPathEffect);
        _paint.setStrokeWidth(_stroke);
    }

    /**
     * @param radius - circle radius
     * @return - the stroke width based on the radius size
     */
    private float getStrokeWidth(final float radius) {
        return radius * 0.06f;
    }

    @Override
    public String getId() {
        return "CircleBorderedTransform";
    }
}
