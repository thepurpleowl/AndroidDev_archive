package surya.com.nu;


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

/**
 * Created by suchaeta on 14-03-2016.
 */
public class Ws extends Fragment {
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
        items.add(new ItemObject(R.drawable.workshops,"WORKSHOPS","-> NASSCOM, TOPIC:IDEAS TO EXECUTION,\t 3:00PM-5:00PM,19TH \n\n-> GOOGLE BUSINESS GROUP, TOPIC:DESIGN SPRINT\t,9:00AM-12:00 NOON ,20TH"));
        return items;
    }
}
