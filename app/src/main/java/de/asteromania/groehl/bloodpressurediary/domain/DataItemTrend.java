package de.asteromania.groehl.bloodpressurediary.domain;

import de.asteromania.groehl.bloodpressurediary.R;

/**
 * Created by jgroehl on 18.09.16.
 */
public enum DataItemTrend {
    POSITIVE(R.mipmap.ic_up),
    NEUTRAL(R.mipmap.ic_equal),
    NEGATIVE(R.mipmap.ic_down);

    DataItemTrend(int image)
    {
        this.image = image;
    }

    int image;

    public int getImage() {
        return image;
    }
}
