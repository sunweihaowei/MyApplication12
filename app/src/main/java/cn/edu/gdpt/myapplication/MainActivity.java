package cn.edu.gdpt.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private List<Map<String,Object>> list;
    private int item_id;
    private SimpleAdapter simpleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        list=new ArrayList<>();
        for (int i=0;i<50;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("tv_title","sunweihao"+i);
            list.add(map);
        }
        simpleAdapter=new SimpleAdapter(MainActivity.this,list,R.layout.item_lv, new String[]{"tv_title"},new int[]{R.id.list_item_tv});
        lv.setAdapter(simpleAdapter);
        registerForContextMenu(lv);

    }

    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) menuInfo;

        item_id=info.position;
        MenuInflater menuInflater=new MenuInflater(MainActivity.this);
        menuInflater.inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_delete:list.remove(item_id);
            simpleAdapter.notifyDataSetChanged();
            break;

        }
        return true;
    }
}
