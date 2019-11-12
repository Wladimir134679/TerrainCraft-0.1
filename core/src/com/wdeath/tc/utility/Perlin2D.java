package com.wdeath.tc.utility;

import java.util.Random;

public class Perlin2D {

    byte[] permutationTable;
    private Random random;
    private long seed;


    public Perlin2D(long seed) {
        this.seed = seed;
        random = new Random(seed);
        permutationTable = new byte[1024];
        random.nextBytes(permutationTable);

    }

    private static float qunticCurve(float t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    static float dot(float[] a, float[] b) {
        return a[0] * b[0] + a[1] * b[1];
    }

    public float noise(float fx, float fy, int octaves) {
        float persistence = 0.5f;
        float amplitude = 1;
        float max = 0;
        float result = 0;

        while (octaves-- > 0) {
            max += amplitude;
            result += Noise(fx, fy) * amplitude;
            amplitude *= persistence;
            fx *= 2;
            fy *= 2;
        }

        return result / max;
    }

    public float Noise(float fx, float fy) {
        // сразу находим координаты левой верхней вершины квадрата
        int left = (int) Math.floor(fx);
        int top = (int) Math.floor(fy);

        // а теперь локальные координаты точки внутри квадрата
        float pointInQuadX = fx - left;
        float pointInQuadY = fy - top;

        // извлекаем градиентные векторы для всех вершин квадрата:
        float[] topLeftGradient = getPseudoRandomGradientVector(left, top);
        float[] topRightGradient = getPseudoRandomGradientVector(left + 1, top);
        float[] bottomLeftGradient = getPseudoRandomGradientVector(left, top + 1);
        float[] bottomRightGradient = getPseudoRandomGradientVector(left + 1, top + 1);

        // вектора от вершин квадрата до точки внутри квадрата:
        float[] distanceToTopLeft = new float[]{pointInQuadX, pointInQuadY};
        float[] distanceToTopRight = new float[]{pointInQuadX - 1, pointInQuadY};
        float[] distanceToBottomLeft = new float[]{pointInQuadX, pointInQuadY - 1};
        float[] distanceToBottomRight = new float[]{pointInQuadX - 1, pointInQuadY - 1};

        // считаем скалярные произведения между которыми будем интерполировать
        float tx1 = dot(distanceToTopLeft, topLeftGradient);
        float tx2 = dot(distanceToTopRight, topRightGradient);
        float bx1 = dot(distanceToBottomLeft, bottomLeftGradient);
        float bx2 = dot(distanceToBottomRight, bottomRightGradient);

        // готовим параметры интерполяции, чтобы она не была линейной:
        pointInQuadX = qunticCurve(pointInQuadX);
        pointInQuadY = qunticCurve(pointInQuadY);

        // собственно, интерполяция:
        float tx = lerp(tx1, tx2, pointInQuadX);
        float bx = lerp(bx1, bx2, pointInQuadX);
        float tb = lerp(tx, bx, pointInQuadY);

        // возвращаем результат:
        return tb;
    }

    private float lerp(float a, float b, float t) {
        return a + (b - a) * t;
    }

    private float[] getPseudoRandomGradientVector(int x, int y) {
        int v = (int) (((x * 1836311903) ^ (y * 2971215073l) + 4807526976l) & 1023);
        v = permutationTable[v] & 3;

        switch (v) {
            case 0:
                return new float[]{1, 0};
            case 1:
                return new float[]{-1, 0};
            case 2:
                return new float[]{0, 1};
            default:
                return new float[]{0, -1};
        }
    }
}
