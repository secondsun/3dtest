package net.saga.game;

import android.content.res.AssetManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import net.saga.game.renderer.MapRenderer;
import net.saga.game.vo.DefaultGameMap;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLView = new GLSurfaceView(this);
        JSONObject mapData;
        try {
            InputStream fileStream = getAssets().open("map.json");
            StringBuilder builder = new StringBuilder(1024);
            int c = -1;
            while ((c = fileStream.read()) != -1) {
                builder.append((char)c);
            }
            mapData = new JSONObject(builder.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



        DefaultGameMap map = new DefaultGameMap(mapData);
        
        mGLView.setRenderer(new MapRenderer(this, map));
        /*ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.raw.robot);
        setContentView(imageView);*/
        setContentView(mGLView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLView.onResume();
    }

    private GLSurfaceView mGLView;
    
}
