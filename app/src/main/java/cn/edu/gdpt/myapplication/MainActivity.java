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
/*
* 总结：listView与ContextMenu
*
*
*
*
* */
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
    //创建ContextMenu，
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //创建适配器上下文信息，得到这个类的方法info.position，即为得到长按项，让其等于一个int类型的值，这里为item_id
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) menuInfo;
        item_id=info.position;
        //菜单加载器，来加载菜单
        MenuInflater menuInflater=new MenuInflater(MainActivity.this);
        menuInflater.inflate(R.menu.menu,menu);
    }
    //上下文的选择项
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            //这里的item_id为刚刚定义的一个值，即为我们想得到的项
            case R.id.menu_delete:list.remove(item_id);
            simpleAdapter.notifyDataSetChanged();
            break;

        }
        return true;
    }
}
