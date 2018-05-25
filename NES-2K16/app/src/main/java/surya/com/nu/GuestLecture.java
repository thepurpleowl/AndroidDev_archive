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

public class GuestLecture extends Fragment {

    public GuestLecture() {
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
        items.add(new ItemObject(R.drawable.keynote,"TALKS","-> Mr.Srikumar Misra, Founder & CEO,MILKMANTRA,6:00PM,18TH \n\n-> Mr.Ravi Ranjan,Head East India,NASSCOM 10000 Startups,NASSCOM ,7:00PM,18TH \n\n-> Mr.Chinmoy Panda,CEO MINDFIRE,10:00AM,19TH \n\n-> Mr.Saumyajit Guha,COO  Calcutta Angels Network,2:30PM,20TH"));
         return items;
    }

}
