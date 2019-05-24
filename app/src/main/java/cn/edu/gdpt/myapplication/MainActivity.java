package cn.edu.gdpt.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 总结：listView与ContextMenu
 *ContextMenu的使用，
 * 1）创建：CreateContextMenu，1.用MenuInflate（菜单），2.listView的布局由adapter来建立，得到item项，最根本的也是在adapter内获取，
 * 在某个位置上长按，给了menu一个信息，即menuInfo，它得到了某个位置的点击，AdapterView.AdapterContextMenuInfo把信息转换为item，将值赋给我们定义的一个值，
 * 创建一个ContextMenu的选项select，得到ContextMenu项，listremove掉adapterView.Adapter的值
 * 静态则name.方法，否则则实例化，或interface和implement接口
 *注意：要通知适配器刷新
 *取值不要重复，这样机器不知道选择哪一个
 *
 * */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lv;
    private List<Map<String, Object>> list;
    private int item_id;
    private SimpleAdapter simpleAdapter;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("tv_title", "sunweihao" + i);
            list.add(map);
        }
        simpleAdapter = new SimpleAdapter(MainActivity.this, list, R.layout.item_lv, new String[]{"tv_title"}, new int[]{R.id.list_item_tv});
        lv.setAdapter(simpleAdapter);
        registerForContextMenu(lv);

    }

    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    //创建ContextMenu，
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //创建适配器上下文信息，得到这个类的方法info.position，即为得到长按项，让其等于一个int类型的值，这里为item_id,（让menu得到长按得值），
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        //拿到项信息，有其他信息可以拿的
        item_id = info.position;
        //菜单加载器，来加载菜单
        MenuInflater menuInflater = new MenuInflater(MainActivity.this);
        menuInflater.inflate(R.menu.menu, menu);
    }

    //上下文的选择项
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //这里的item_id为刚刚定义的一个值，即为我们想得到的项
            case R.id.menu_delete:
                list.remove(item_id);
                simpleAdapter.notifyDataSetChanged();
                break;

        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
              startActivity(new Intent(MainActivity.this,Main2Activity.class));
                break;
        }
    }
}
