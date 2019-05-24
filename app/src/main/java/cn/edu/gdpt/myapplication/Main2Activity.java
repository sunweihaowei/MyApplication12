package cn.edu.gdpt.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
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

public class Main2Activity extends AppCompatActivity {
    private int Item;
    private ListView lv2;
    private List<Map<String,Object>> list=new ArrayList<>();
    SimpleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        for (int i=0;i<50;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("title","你好，你完成了"+i);
            list.add(map);
        }
        //layout可共用
        adapter=new SimpleAdapter(Main2Activity.this,list,R.layout.item_lv,
                new String[]{"title"},
                new int[]{R.id.list_item_tv});
        lv2.setAdapter(adapter);
        registerForContextMenu(lv2);
    }

    private void initView() {
        lv2 = (ListView) findViewById(R.id.lv2);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) menuInfo;
        Item=info.position;
        MenuInflater menuInflater=new MenuInflater(Main2Activity.this);
        menuInflater.inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_delete:list.remove(Item);
            adapter.notifyDataSetChanged();
            break;
        }
        return true;
    }
}
