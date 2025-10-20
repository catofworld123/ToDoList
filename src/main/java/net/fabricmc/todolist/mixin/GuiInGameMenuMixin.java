package net.fabricmc.todolist.mixin;



import net.fabricmc.todolist.GuiListManager;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiIngameMenu;
import net.minecraft.src.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngameMenu.class)
public class GuiInGameMenuMixin extends GuiScreen {

    @Inject(method = "initGui", at = @At("TAIL"))
    private void registerMyButton(CallbackInfo ci){
        this.buttonList.add(new GuiButton(14, this.width / 2 - 100, this.height / 4 + 24 + 32,"To Do List Settings"));
    }
    @Inject(method = "actionPerformed", at = @At("TAIL"))
    private void myButtonclic(GuiButton par1GuiButton, CallbackInfo ci){
        if (par1GuiButton.id == 14){
            this.mc.displayGuiScreen(new GuiListManager(this));
        }
    }

}
