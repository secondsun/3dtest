package net.saga.game.vo;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Summers on 8/3/13.
 */
public class DefaultGameMap {

    private static String TAG = DefaultGameMap.class.getSimpleName();

    public int tilesWidth = 64;
    public int tilesHeight = 64;
    public int width = 8;
    public int height = 8;

    public final Tile[][] tiles;

    public DefaultGameMap(JSONObject mapData) {

        try {
            height = mapData.getInt("height");
            width = mapData.getInt("width");
            JSONArray jsonMap = mapData.getJSONArray("map");
            tiles = new Tile[height][width];
            for (int y = 0; y < height; y++) {
                JSONArray mapRow = jsonMap.getJSONArray(y);
                for (int x = 0; x < width; x++) {
                    int height = mapRow.getInt(x);
                    tiles[y][x] = new Tile(x,y,height);
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e);
            throw new IllegalArgumentException("Provided map data was not correct." + mapData.toString(), e);
        }
    }

    public Tile getTileAt(int tileX, int tileY) {
        return tiles[tileY][tileX];
    }

    public void draw(GL10 gl) {
        gl.glPushMatrix();
        gl.glTranslatef(width / -2.0f, 0 , height / -2.0f );
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                gl.glPushMatrix();
                gl.glTranslatef(tile.x , tile.z, tile.y  );
                tile.draw(gl);
                gl.glPopMatrix();
            }
        }
        gl.glPopMatrix();
    }

}
