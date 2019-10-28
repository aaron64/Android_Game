#ifdef GL_ES
    precision mediump float;
#endif

uniform mat4 u_projTrans;
varying vec2 v_texCoords;

uniform sampler2D u_texture;

uniform float u_time;
uniform float u_count;
uniform float u_dir;

void main() {
    float ratio = u_count/u_time;
    vec2 uv = vec2(v_texCoords);
    uv.y -= 1.0f;

    vec4 color = texture2D(u_texture, v_texCoords).rgba;

    uv.y += ratio * u_dir;

    float tilt = uv.x * sin(ratio * 3.14f + 3.14f / 0.5f);

    float grad = sin(uv.y * 3.14f + tilt * 1.3f) * 2.0f;
    color.a *= grad;

    gl_FragColor = color;
}