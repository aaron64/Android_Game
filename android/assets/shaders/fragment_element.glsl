#ifdef GL_ES
    precision mediump float;
#endif

uniform mat4 u_projTrans;
varying vec4 v_color;
varying vec2 v_texCoords;

uniform vec4 u_highlight;
uniform vec4 u_mid;
uniform vec4 u_shadow;

uniform sampler2D u_texture;

void main() {

        vec4 color = texture2D(u_texture, v_texCoords).rgba;
        vec4 out_col = vec4(0);

        out_col += u_highlight * color.r * color.a;
        out_col += u_mid * color.g * color.a;
        out_col += u_shadow * color.b * color.a;

        gl_FragColor = out_col;
}