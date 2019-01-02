#ifdef GL_ES
    precision mediump float;
#endif

uniform mat4 u_projTrans;
varying vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;

void main() {
        vec4 color = texture2D(u_texture, v_texCoords).rgba;
        color.g = 0.0;
        color.b = 0.0;
        //float gray = (color.r + color.g + color.b) / 3.0;
        //vec4 grayscale = vec4(gray, color.a);

        gl_FragColor = vec4(color);
}