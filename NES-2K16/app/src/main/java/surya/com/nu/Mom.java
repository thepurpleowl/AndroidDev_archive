package surya.com.nu;

/**
 * Created by suchaeta on 23-01-2016.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Mom extends Fragment {

    public Mom() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.momhome, container, false);
        ImageView img=(TouchImageView)view.findViewById(R.id.imageView);


        return view;
    }

}
