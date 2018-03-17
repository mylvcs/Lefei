package com.example.wangmengyun.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wangmengyun.lefei.R;

/**
 * Created by wangmengyun on 2018/3/17.
 */
public class AddressActivity extends AppCompatActivity {

    private TextView mTvLeft;
    private TextView mTvRight;
    private Button mBtn;
    private RelativeLayout mRlLeft;
    private RelativeLayout mRlRight;
    private WindowManager mWindowManager;
    private int[] mLeftLocation;
    private int[] mRightLocation;
    private Bitmap mLeftCacheBitmap;
    private Bitmap mRightCacheBitmap;
    private LinearLayout mLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        mWindowManager = getWindowManager();

        mTvLeft = (TextView) findViewById(R.id.tv_left);
        mTvRight = (TextView) findViewById(R.id.tv_right);
        mRlLeft = (RelativeLayout) findViewById(R.id.rl_left);
        mRlRight = (RelativeLayout) findViewById(R.id.rl_right);
        mLl = (LinearLayout) findViewById(R.id.ll);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textAnim();
                mBtn.setEnabled(false);
            }
        });
    }


    /**
     * 左边tv的镜像view
     */
    private ImageView copyViewLeft;
    /**
     * 右边tv的镜像view
     */
    private ImageView copyViewRight;

    /**
     * 获取tv的属性,计算偏移量,
     */
    private void textAnim() {

        //获取tv控件距离父控件的位置
        int leftRight = mTvLeft.getRight();
        int rightLeft = mTvRight.getLeft();

        //包裹右侧tv距离父控件的距离
        int rlRight = mRlRight.getRight();
        int rlLeft = mRlRight.getLeft();
        //在哪里设的padding就要用哪个控件来获取padding值
        int paddingStart = mLl.getPaddingStart();

        Log.d("AddressActivity", "paddingStart:" + paddingStart);

        //左侧textview需要移动的距离
        int leftOffset = rlRight - leftRight - paddingStart;
        //右侧textview需要移动的距离
        int rightOffset = rlLeft + rightLeft - paddingStart;

        //创建出镜像view
        createCopyView();

        //隐藏掉两边的tv
        mTvLeft.setVisibility(View.INVISIBLE);
        mTvRight.setVisibility(View.INVISIBLE);

        //开启镜像view的动画
        leftAnim(leftOffset,mLeftLocation[0]);
        rightAnim(rightOffset,mRightLocation[0]);
    }

    /**
     * 创建镜像view
     */
    private void createCopyView(){
        mLeftLocation = new int[2];
        mRightLocation = new int[2];
        //获取相对window的坐标
        mTvLeft.getLocationInWindow(mLeftLocation);
        mTvRight.getLocationInWindow(mRightLocation);

        //获取左边tv的缓存bitmap
        mTvLeft.setDrawingCacheEnabled(true);
        mLeftCacheBitmap = Bitmap.createBitmap(mTvLeft.getDrawingCache());
        mTvLeft.destroyDrawingCache();
        //获取右边tv的缓存bitmap
        mTvRight.setDrawingCacheEnabled(true);
        mRightCacheBitmap = Bitmap.createBitmap(mTvRight.getDrawingCache());
        mTvRight.destroyDrawingCache();

        //创建出两个镜像view
        copyViewLeft = createCopyView(mLeftLocation[0], mLeftLocation[1], mLeftCacheBitmap);
        copyViewRight = createCopyView(mRightLocation[0], mRightLocation[1], mRightCacheBitmap);
        //释放bitmap资源...这我不确定是不是这么做
        mLeftCacheBitmap = null;
        mRightCacheBitmap = null;
    }
    /**
     * 左侧镜像view的动画
     * @param offset    偏移量
     * @param defX      原始位置的x
     */
    private void leftAnim(int offset, final int defX){
        ValueAnimator leftAnimV = ValueAnimator.ofInt(0,offset);
        leftAnimV.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int animatedValue = (int) valueAnimator.getAnimatedValue();
                WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) copyViewLeft.getLayoutParams();
                //往右边移动所以x是变大的
                layoutParams.x = defX + animatedValue;
                mWindowManager.updateViewLayout(copyViewLeft,layoutParams);
            }
        });
        leftAnimV.setDuration(400);
        leftAnimV.start();
        //左侧动画监听
        leftAnimV.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //改变值
                String s = mTvLeft.getText().toString();
                mTvLeft.setText(mTvRight.getText().toString());
                mTvRight.setText(s);
                mTvLeft.setVisibility(View.VISIBLE);
                mWindowManager.removeView(copyViewLeft);
                copyViewLeft = null;
                mBtn.setEnabled(true);
            }
        });
    }

    /**
     * 右侧镜像view动画
     * @param offset    偏移量
     * @param defX      原始位置的x
     */
    private void rightAnim(int offset, final int defX){
        ValueAnimator rightAnimV = ValueAnimator.ofInt(0,offset);
        rightAnimV.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int animatedValue = (int) valueAnimator.getAnimatedValue();
                WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) copyViewRight.getLayoutParams();
                layoutParams.x = defX - animatedValue;
                mWindowManager.updateViewLayout(copyViewRight,layoutParams);
            }
        });
        rightAnimV.setDuration(400);
        rightAnimV.start();
        rightAnimV.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mTvRight.setVisibility(View.VISIBLE);
                mWindowManager.removeView(copyViewRight);
                copyViewRight = null;
            }
        });
    }

    /**
     * 创建镜像view
     *
     * @param x
     * @param y
     * @param bitmap
     */
    private ImageView createCopyView(int x, int y, Bitmap bitmap) {
        WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.format = PixelFormat.TRANSLUCENT;            //图片之外其他地方透明
        mLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        mLayoutParams.x = x;   //设置imageView的原点
        mLayoutParams.y = y - getStatusHeight(this);
        mLayoutParams.alpha = 1f;                                //设置透明度
        mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        ImageView copyView = new ImageView(this);
        copyView = new ImageView(this);
        copyView.setImageBitmap(bitmap);
        mWindowManager.addView(copyView, mLayoutParams);   //添加该iamgeView到window
        return copyView;
    }

    /**
     * 获取状态栏的高度
     * @param context
     * @return
     */
    private static int getStatusHeight(Context context) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = context.getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }
}