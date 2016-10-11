package com.dl7.myapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dl7.helperlibrary.adapter.BaseQuickAdapter;
import com.dl7.helperlibrary.adapter.BaseViewHolder;
import com.dl7.myapp.R;
import com.dl7.myapp.local.table.BeautyPhotoBean;
import com.dl7.myapp.module.bigphoto.BigPhotoActivity;
import com.dl7.myapp.module.love.LoveActivity;
import com.dl7.myapp.utils.DefIconFactory;
import com.dl7.myapp.utils.ImageLoader;
import com.dl7.myapp.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by long on 2016/9/22.
 * 美图 Adapter
 */
public class BeautyPhotosAdapter extends BaseQuickAdapter<BeautyPhotoBean> {

    // 图片的宽度
    private int mPhotoWidth;


    public BeautyPhotosAdapter(Context context) {
        super(context);
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        int marginPixels = context.getResources().getDimensionPixelOffset(R.dimen.photo_margin_width);
        mPhotoWidth = widthPixels / 2 - marginPixels;
    }

    public BeautyPhotosAdapter(Context context, List<BeautyPhotoBean> data) {
        super(context, data);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.adapter_beauty_photos;
    }

    @Override
    protected void convert(final BaseViewHolder holder, final BeautyPhotoBean item) {
        final ImageView ivPhoto = holder.getView(R.id.iv_photo);
        int photoHeight = StringUtils.calcPhotoHeight(item.getPixel(), mPhotoWidth);
        // 接口返回的数据有像素分辨率，根据这个来缩放图片大小
        final ViewGroup.LayoutParams params = ivPhoto.getLayoutParams();
        params.width = mPhotoWidth;
        params.height = photoHeight;
        ivPhoto.setLayoutParams(params);
        ImageLoader.loadFitCenter(mContext, item.getImgsrc(), ivPhoto, DefIconFactory.provideIcon());
        holder.setText(R.id.tv_title, item.getTitle());
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContext instanceof LoveActivity) {
                    BigPhotoActivity.launchForResult((Activity) mContext, (ArrayList<BeautyPhotoBean>) getData(), holder.getAdapterPosition());
                } else {
                    BigPhotoActivity.launch(mContext, (ArrayList<BeautyPhotoBean>) getData(), holder.getAdapterPosition());
                }
            }
        });
    }

}
