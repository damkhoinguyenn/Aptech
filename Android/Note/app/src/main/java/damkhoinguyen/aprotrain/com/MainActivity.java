package damkhoinguyen.aprotrain.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[]{
            DBHelper.ID,
            DBHelper.DESC,
            DBHelper.DATE
    };

    final int[] to = new int[]{
            R.id.id,
            R.id.desc,
            R.id.time
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = findViewById(R.id.listNotes);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.list_item, cursor, from, to, 0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, viewid) -> {
            TextView idTextView = view.findViewById(R.id.id);
            TextView descTextView = view.findViewById(R.id.desc);
            TextView timeTextView = view.findViewById(R.id.time);

            String id = idTextView.getText().toString();
            String desc = descTextView.getText().toString();
            String time = timeTextView.getText().toString();

            Intent modifyIntent = new Intent(getApplicationContext(), NoteModify.class);
            modifyIntent.putExtra("id",id);
            modifyIntent.putExtra("desc",desc);
            modifyIntent.putExtra("time",time);
            startActivity(modifyIntent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.add_record){
            Intent addNew = new Intent(this, AddNote.class);
            startActivity(addNew);
        }
        return super.onOptionsItemSelected(item);
    }
}