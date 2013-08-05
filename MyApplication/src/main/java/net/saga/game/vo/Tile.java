package net.saga.game.vo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Summers on 8/3/13.
 */
public class Tile {
    private final FloatBuffer mVertexBuffer;
    private final FloatBuffer mTexBuffer;
    private final ByteBuffer mIndexBuffer;
    public float x,y,z;

    private static final float ONE = 0.5f;

    //The 24 vertex of the 3d cube (the skybox)
    static final float[] vertices = {
        -ONE, -ONE, -ONE, //face 1 (front)
        -ONE, ONE, -ONE,
            ONE, -ONE, -ONE,
            ONE, ONE, -ONE,

            ONE, ONE, ONE, //face 2 (back)
            ONE, -ONE, ONE,
        -ONE, ONE, ONE,
        -ONE, -ONE, ONE,

        -ONE, -ONE, -ONE, //face 3 (left)
        -ONE, -ONE, ONE,
        -ONE, ONE, -ONE,
        -ONE, ONE, ONE,

            ONE, ONE, ONE, //face 4 (right)
            ONE, ONE, -ONE,
            ONE, -ONE, ONE,
            ONE, -ONE, -ONE,

        -ONE, ONE, -ONE, // face 5 (up)
        -ONE, ONE, ONE,
            ONE, ONE, ONE,
            ONE, ONE, -ONE,

            ONE, -ONE, ONE, //face 6 (bot)
            ONE, -ONE, -ONE,
        -ONE, -ONE, -ONE,
        -ONE, -ONE, ONE
    };

    //Coordinate of the textures
    public static final float[] texCoords = {
        0.0f, 0.0f,
        0.0f, 1.0f,
        1.0f, 0.0f,
        1.0f, 1.0f,

        0.0f, 0.0f,
        0.0f, 1.0f,
        1.0f, 0.0f,
        1.0f, 1.0f,

        0.0f, 0.0f,
        0.0f, 1.0f,
        1.0f, 0.0f,
        1.0f, 1.0f,

        0.0f, 0.0f,
        0.0f, 1.0f,
        1.0f, 0.0f,
        1.0f, 1.0f,


        0.0f, 1.0f,
        0.0f, 0.0f,
        1.0f, 0.0f,
        1.0f, 1.0f,

        0.0f, 1.0f,
        0.0f, 0.0f,
        1.0f, 0.0f,
        1.0f, 1.0f,
    };

//Defining triangles based on the vertice array indexes
    final byte[] elements = {
        0, 2, 1,
        2, 1, 3,

        4, 5, 6,
        5, 6, 7,

        8, 9, 10,
        9, 10, 11,

        12, 13, 14,
        13, 14, 15,

        16, 17, 18,
        18, 19, 16,

        20, 21, 22,
        22, 23, 20
    };

    public Tile(float x, float y, float z) {
        this.z = z;
        this.y = y;
        this.x = x;

        // Buffers to be passed to gl*Pointer() functions
        // must be direct, i.e., they must be placed on the
        // native heap where the garbage collector cannot
        // move them.
        //
        // Buffers with multi-byte datatypes (e.g., short, int, float)
        // must have their byte order set to native order

        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());
        mVertexBuffer = vbb.asFloatBuffer();
        mVertexBuffer.put(vertices);
        mVertexBuffer.position(0);

        ByteBuffer cbb = ByteBuffer.allocateDirect(texCoords.length*4);
        cbb.order(ByteOrder.nativeOrder());
        mTexBuffer = cbb.asFloatBuffer();
        mTexBuffer.put(texCoords);
        mTexBuffer.position(0);


        ByteBuffer ibb = ByteBuffer.allocateDirect(elements.length*4);
        ibb.order(ByteOrder.nativeOrder());
        mIndexBuffer = ibb;
        mIndexBuffer.put(elements);
        mIndexBuffer.position(0);

    }

    public void draw(GL10 gl) {
        gl.glFrontFace(GL10.GL_CCW);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTexBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, elements.length,
                GL10.GL_UNSIGNED_BYTE, mIndexBuffer);
    }

}
