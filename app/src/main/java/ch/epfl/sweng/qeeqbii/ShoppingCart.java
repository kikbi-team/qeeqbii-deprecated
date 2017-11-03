package ch.epfl.sweng.qeeqbii;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidcleres on 01.11.17.
 */
public class ShoppingCart implements Parcelable {
    private List<Product> m_items = new ArrayList<Product>();

    public ShoppingCart(){
        //To avoid an empty list
        addItemToCart(new Product("Please Click on + to add an item", "0 mg", "Stuff", "cool Nutrients"));
    }

    public void addItemToCart(Product product) {
        m_items.add(product);
    }

    public void addSpecificItemInCart(int index, Product product) {
        m_items.add(index, product);
    }

    public List<Product> getItemsInCart() {
        return m_items;
    }
    public Product getSpecificItemInCart(int index) {
        return m_items.get(index);
    }

    protected ShoppingCart(Parcel in) {
        if (in.readByte() == 0x01) {
            m_items = new ArrayList<Product>();
            in.readList(m_items, Product.class.getClassLoader());
        } else {
            m_items = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (m_items == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(m_items);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ShoppingCart> CREATOR = new Parcelable.Creator<ShoppingCart>() {
        @Override
        public ShoppingCart createFromParcel(Parcel in) {
            return new ShoppingCart(in);
        }

        @Override
        public ShoppingCart[] newArray(int size) {
            return new ShoppingCart[size];
        }
    };
}