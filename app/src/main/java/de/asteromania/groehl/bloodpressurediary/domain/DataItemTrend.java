package de.asteromania.groehl.bloodpressurediary.domain;

import de.asteromania.groehl.bloodpressurediary.R;

/**
 * Created by jgroehl on 18.09.16.
 */
public enum DataItemTrend {
    DECREASING(R.mipmap.ic_down),
    NEUTRAL(R.mipmap.ic_equal),
    INCREASING(R.mipmap.ic_up);

    DataItemTrend(int image)
    {
        this.image = image;
    }

    int image;

    public int getImage() {
        return image;
    }
}
