package net.fabricmc.todolist;

import net.fabricmc.todolist.util.GuiScrollingList;
import net.minecraft.src.*;


public class GuiListManager extends GuiIngameMenu {

    private GuiScreen parentGuiScreen;

    public GuiListManager(GuiScreen parent) {
        this.parentGuiScreen = parent;

    }




    public FontRenderer getFontRenderer(){
        return this.fontRenderer;
    }


    Minecraft getMinecraftInstance() {

        return mc;
    }




    private String ReturnTrueFalse(boolean b){
        if (!b){
            return "§cFalse";
        }
        else return "§aTrue";

    }


    private GuiTextField goalField;
    private GuiButton doneBtn;
    ListManager manager = new ListManager();

    private int lineAmount = ListManager.loadLineNum();

    private GuiSlotToDoList todoList;

    public int GetLineAmount(){
        return this.lineAmount;
    }
    public void SetLineAmount(int amount){
        ListManager.saveLineNum(amount);
    }

    @Override
    public void initGui() {



        if (manager.getMode().equals("To Do List")){
            if (manager.getMode().isBlank()){
                manager.setMode("To Do List");
            }
        }
        int a;
        int b;



        if (manager.getMode().equals("To Do List")){
             a = 120;
             b = 72;

            this.buttonList.add(this.doneBtn = (new GuiButton(0, this.width / 2 - 100-b, 124 + a, "Done")));
            this.buttonList.add((new GuiButton(1, this.width / 2 - 100-b, 100 + a, "Show List: " + ReturnTrueFalse(manager.getoverlayConfig()))));
            this.buttonList.add((new GuiButton(2, this.width / 2 - 100-b, 76 + a, "List Type: " + manager.getMode())));

            this.buttonList.add(new GuiSmallButton(3,this.width/2+100-b,76+a,"§aIncrease By 1"));
            this.buttonList.add(new GuiSmallButton(4,this.width/2+100-b,100+a,"Line Amount: " + lineAmount));
            this.buttonList.add(new GuiSmallButton(5,this.width/2+100-b,124+a,"§cDecrease By 1"));


            this.goalField = (new GuiTextField(this.fontRenderer, this.width / 2 - 150, 60, 300, 20));
            this.goalField.setMaxStringLength(32767);
            this.goalField.setFocused(true);
            this.goalField.setText(" " + manager.GetGoal().trim());
            this.todoList = new GuiSlotToDoList(this);
            this.todoList.registerScrollButtons(this.buttonList, 7,8);



        }
        else {
            a = 10;
            this.buttonList.add(this.doneBtn = (new GuiButton(0, this.width / 2 - 100, 124 + a, "Done")));
            this.buttonList.add((new GuiButton(1, this.width / 2 - 100, 100 + a, "Show List: " + ReturnTrueFalse(manager.getoverlayConfig()))));
            this.buttonList.add((new GuiButton(2, this.width / 2 - 100, 76 + a, "List Type: " + manager.getMode())));
            this.goalField = (new GuiTextField(this.fontRenderer, this.width / 2 - 150, 60, 300, 20));
            this.goalField.setMaxStringLength(32767);
            this.goalField.setFocused(true);
            this.goalField.setText(" " + manager.GetGoal().trim());
            this.todoList = new GuiSlotToDoList(this);
            this.todoList.registerScrollButtons(this.buttonList, 7,8);
        }

    }
    @Override
    protected void actionPerformed(GuiButton guiButton) {
        if (guiButton.enabled) {
            if (guiButton.id == 0) {
                manager.SetGoal(goalField.getText());
                this.mc.displayGuiScreen(this.parentGuiScreen);
            }
            if (guiButton.id == 1){
                manager.setoverlayConfig(!manager.getoverlayConfig());
                guiButton.displayString = "Show List: " + ReturnTrueFalse(manager.getoverlayConfig());
            }
            if (guiButton.id == 2){
                if (manager.getMode().equals("To Do List")){

                    manager.setMode("Goal");
                    guiButton.displayString = "List Type: " + manager.getMode() ;
                    int a = 10;
                    this.buttonList.clear();
                    this.buttonList.add(this.doneBtn = (new GuiButton(0, this.width / 2 - 100, 124 + a, "Done")));
                    this.buttonList.add((new GuiButton(1, this.width / 2 - 100, 100 + a, "Show List: " + ReturnTrueFalse(manager.getoverlayConfig()))));
                    this.buttonList.add((new GuiButton(2, this.width / 2 - 100, 76 + a, "List Type: " + manager.getMode())));

                }
                else{

                    manager.setMode("To Do List");
                    guiButton.displayString = "List Type: " + manager.getMode() ;
                    int a = 120;
                    int b = 72;

                    this.buttonList.clear();
                    this.buttonList.add(this.doneBtn = (new GuiButton(0, this.width / 2 - 100-b, 124 + a, "Done")));
                    this.buttonList.add((new GuiButton(1, this.width / 2 - 100-b, 100 + a, "Show List: " + ReturnTrueFalse(manager.getoverlayConfig()))));
                    this.buttonList.add((new GuiButton(2, this.width / 2 - 100-b, 76 + a, "List Type: " + manager.getMode())));
                    this.buttonList.add(new GuiSmallButton(3,this.width/2+100-b,76+a,"§aIncrease By 1"));
                    this.buttonList.add(new GuiSmallButton(4,this.width/2+100-b,100+a,"Line Amount: " + lineAmount));
                    this.buttonList.add(new GuiSmallButton(5,this.width/2+100-b,124+a,"§cDecrease By 1"));

                }

            }
            if (manager.getMode().equals("To Do List")) {
                if (guiButton.id == 7 || guiButton.id == 8) {
                    todoList.actionPerformed(guiButton);
                }
                if (guiButton.id == 3){
                    lineAmount += 1;
                    ListManager.saveLineNum(lineAmount);
                    GuiButton button2 = (GuiButton) this.buttonList.get(4);
                    button2.displayString = "Line Amount: " + lineAmount;
                }
                if (guiButton.id == 5 && lineAmount > 1){
                    lineAmount -= 1;
                    ListManager.saveLineNum(lineAmount);
                    GuiButton button2 = (GuiButton) this.buttonList.get(4);
                    button2.displayString = "Line Amount: " + lineAmount;
                }
            }

        }
    }
    @Override
    protected void keyTyped(char c, int i) {

            this.goalField.textboxKeyTyped(c, i);
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
            this.goalField.mouseClicked(i, j, k);

    }
    @Override
    public void updateScreen() {

            this.goalField.updateCursorCounter();

    }

    @Override
    public void drawScreen(int i, int j, float f) {

        if (manager.getMode().equals("To Do List")){
            this.drawDefaultBackground();
            this.drawCenteredString(this.fontRenderer, "Manage To Do List", this.width / 2, 20, 16777215);
            for (int var4 = 0; var4 < this.buttonList.size(); ++var4) {
                GuiButton var5 = (GuiButton) this.buttonList.get(var4);
                var5.drawButton(this.mc, i, j);
            }
            this.todoList.drawScreen(i,j,f);
        }
        else{
            this.drawDefaultBackground();
            this.drawCenteredString(this.fontRenderer, "Set Goal", this.width / 2, 20, 16777215);
            this.goalField.drawTextBox();
            for (int var4 = 0; var4 < this.buttonList.size(); ++var4) {
                GuiButton var5 = (GuiButton) this.buttonList.get(var4);
                var5.drawButton(this.mc, i, j);
            }
        }

    }

}
