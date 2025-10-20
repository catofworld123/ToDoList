package net.fabricmc.todolist;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiIngameMenu;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.GuiTextField;

import java.io.IOException;

public class GuiChangeToDoListInstance extends GuiIngameMenu {
    private GuiSlotToDoList parentGuiScreen;
    private GuiButton doneBtn;
    private GuiTextField entryField;
    private String text;
    private int index;
    private boolean enabled = true;
    public GuiChangeToDoListInstance(GuiSlotToDoList parent,String text,int index){
        this.parentGuiScreen = parent;
        this.text = text;
        this.index = index;
    }
    @Override
    public void initGui() {
        this.buttonList.add(this.doneBtn = (new GuiButton(0, this.width / 2 - 100, 124, "Done")));
        this.entryField = (new GuiTextField(this.fontRenderer, this.width / 2 - 150, 60, 300, 20));
        this.entryField.setMaxStringLength(32767);
        this.entryField.setFocused(true);
        this.entryField.setText("" + text);
        this.enabled = true;
    }
    @Override
    protected void keyTyped(char c, int i) {

        this.entryField.textboxKeyTyped(c, i);
        if (this.doneBtn.enabled) {
            if (i != 28 && i != 156) {
                if (i == 1) {
                    this.actionPerformed(this.doneBtn);
                }
            } else {
                this.actionPerformed(this.doneBtn);
            }
        }
    }
    @Override
    protected void mouseClicked(int i, int j, int k) {

        super.mouseClicked(i, j, k);
        this.entryField.mouseClicked(i, j, k);

    }
    @Override
    public void updateScreen() {

        this.entryField.updateCursorCounter();
    }
    @Override
    public void drawScreen(int i, int j, float f) {
        if (enabled) {
            this.drawDefaultBackground();
            this.drawCenteredString(this.fontRenderer, "Set Entry", this.width / 2, 20, 16777215);
            for (int var4 = 0; var4 < this.buttonList.size(); ++var4) {
                GuiButton var5 = (GuiButton) this.buttonList.get(var4);
                var5.drawButton(this.mc, i, j);
            }
            this.entryField.drawTextBox();
        }
        else{
            this.onGuiClosed();
        }
    }
    @Override
    protected void actionPerformed(GuiButton guiButton) {
        if (guiButton.enabled) {
            if (guiButton.id == 0) {
                try {
                    parentGuiScreen.storage.saveString(index, entryField.getText());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                this.enabled = false;
                this.mc.displayGuiScreen(this.parentGuiScreen.getParentGuiScreen());

            }
        }
    }
}
