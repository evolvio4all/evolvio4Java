/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evolvio.models;

import evolvio.textures.Texture;

/**
 *
 * @author Quentin
 */
public class TexturedModel {

    private RawModel model;
    private Texture texture;

    public TexturedModel(RawModel model, Texture texture) {
        this.model = model;
        this.texture = texture;
    }

    public RawModel getModel() {
        return model;
    }

    public Texture getTexture() {
        return texture;
    }

}
