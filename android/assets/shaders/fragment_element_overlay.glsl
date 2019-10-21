#ifdef GL_ES
    precision mediump float;
#endif

uniform mat4 u_projTrans;
varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform sampler2D u_texture_overlay;

uniform float u_time;

void main() {
        vec2 uv = vec2(v_texCoords);
        uv.y += u_time/160.0f;
        uv.y = fract(uv.y);

        float grad = (v_texCoords.y - 0.5f) * 2.0f;

        float alpha = texture2D(u_texture, v_texCoords).a;
        vec4 color = vec4(texture2D(u_texture_overlay, uv).rgba);

        //color.rgb = ((color.rgb - 0.5f) * max(1.0f, 0.0f)) + 0.5f;

        color *=  grad * alpha;

        gl_FragColor = vec4(color);
}