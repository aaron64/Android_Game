package com.mygdx.game.GUI;

import com.mygdx.game.util.Vec2f;

public class GUIHPanel extends GUIComponent {

    public GUIHPanel(GUI gui, String name, GUIComponent parent, Vec2f size) {
        super(gui, name, parent, size);
    }

    @Override
    public void setChildPos() {
        float offset = 0;

        switch(horizontalAnchor) {
            case LEFT:
                offset = pos.x;
                break;
            case RIGHT:
                offset = pos.x + size.w();
                break;
            case CENTER:
                float totalWidth = 0;
                for(GUIComponent child : children) {
                    totalWidth += child.leftMargin() + child.getSize().x + child.rightMargin();
                }
                offset = (pos.x + size.w()/2) - totalWidth/2;
                break;
        }

        for (GUIComponent child : children) {

            Vec2f cPos = new Vec2f();

            switch (horizontalAnchor) {
                case LEFT: {
                    offset += child.leftMargin();
                    cPos.x = offset;

                    float rightEnd = pos.x + size.w();
                    if(cPos.x + child.getSize().w() + child.rightMargin() > rightEnd)
                        child.getSize().x = (int)(rightEnd - cPos.x - child.rightMargin());

                    offset += child.getSize().x + child.rightMargin();
                }
                break;
                case RIGHT: {
                    offset -= child.rightMargin() + child.getSize().x;
                    cPos.x = offset;

                    float leftEnd = pos.x;
                    if(cPos.x < leftEnd + child.leftMargin())
                        child.getSize().x = (int)(cPos.x + child.size.w() - (leftEnd + child.leftMargin()));

                    offset -= child.leftMargin();
                }
                break;
                case CENTER: {
                    offset += child.leftMargin();
                    cPos.x = offset;
                    offset += child.getSize().x + child.rightMargin();
                }
                break;
            }

            switch (verticalAnchor) {
                case TOP: {
                    cPos.y = pos.y + size.h() - child.getSize().y - child.topMargin();

                    /*float bottomEnd = pos.y;
                    if(cPos.y < bottomEnd + child.bottomMargin())
                        child.getSize().y = (int)(cPos.y - (bottomEnd + child.bottomMargin()));*/
                }
                break;
                case BOTTOM: {
                    cPos.y = pos.y + child.bottomMargin();

                    /*float topEnd = pos.y + size.h();
                    if(cPos.y + child.getSize().h() + child.topMargin() > topEnd)
                        child.getSize().y = (int)(topEnd - cPos.y - child.topMargin());*/
                }
                break;
                case CENTER: {
                    cPos.y = (pos.y + size.h()/2) - child.getSize().y/2;
                }
                break;
            }

            child.setPos(cPos);
            child.setChildPos();
        }
    }
}
