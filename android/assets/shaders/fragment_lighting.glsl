#ifdef GL_ES
    precision mediump float;
#endif

uniform mat4 u_projTrans;
varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform sampler2D u_lightmap;

void main() {
        vec2 uv = vec2(v_texCoords);

        vec3 col = texture2D(u_texture, uv).rgb;
        vec3 light = texture2D(u_lightmap, uv).rgb;

        vec3 intensity = vec3(dot(col, vec3(light.r)));
        float alpha = texture2D(u_texture, uv).a;

        gl_FragColor = vec4(mix(intensity, col, light.r) * light, alpha);
}