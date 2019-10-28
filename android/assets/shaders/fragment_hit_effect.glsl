#ifdef GL_ES
    precision mediump float;
#endif

uniform mat4 u_projTrans;
varying vec2 v_texCoords;

uniform sampler2D u_texture;

uniform float u_time;
uniform float u_count;

void main() {
    float ratio = u_count/u_time;
    vec2 uv = vec2(v_texCoords);

    vec4 color = texture2D(u_texture, uv).rgba;


    float d = 1.0f - floor(distance(uv, vec2(0.25f, 0.5f)) / ratio);
    float d2 = floor(distance(uv, vec2(0.25f, 0.5f)) / (ratio / 2.0f));

    color.a *= (d * d2);

    gl_FragColor = color;
}