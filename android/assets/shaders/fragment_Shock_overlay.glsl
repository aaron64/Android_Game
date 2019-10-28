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

        float alpha = texture2D(u_texture, v_texCoords).a;
        vec4 color = vec4(texture2D(u_texture_overlay, uv).rgba);

        color *=  grad * alpha;

        gl_FragColor = vec4(color);
}