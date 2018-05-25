package surya.com.nu;

/**
 * Created by suchaeta on 23-01-2016.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Workshop extends Fragment {

    public Workshop() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pop_music, container, false);
        GridView gridview = (GridView)view.findViewById(R.id.gridview);

        List<ItemObject> allItems = getAllItemObject();
        CustomAdapter customAdapter = new CustomAdapter(getActivity(), allItems);
        gridview.setAdapter(customAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Position: " + position, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private List<ItemObject> getAllItemObject(){
        List<ItemObject> items = new ArrayList<>();
        items.add(new ItemObject(R.drawable.innocamp,"INNOVATION CAMP","8:00AM-8:00PM,19TH & 20TH\nJUDGES FROM NASSCOM, MSME, NSIC, EDI, USF and TiE."));
        items.add(new ItemObject(R.drawable.negosyo,"NEGOSYO","19TH & 20TH, \nIN ASSOCIATION WITH NASSCOM, CALCUTTA ANGELS NETWORK & USF "));
        items.add(new ItemObject(R.drawable.app,"APP MAKING","ONLINE EVENT"));
        items.add(new ItemObject(R.drawable.ad,"AD MAKING","THEME :HUMANS IMPACT ON WILDLIFE ! \n" +
                "ONLINE EVENT"));
        items.add(new ItemObject(R.drawable.fun,"TRADE VOYAGE","19TH MORNING & 20TH SECOND HALF"));
        return items;
    }

}
