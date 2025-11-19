package net.fabricmc.todolist.mixin;



import net.fabricmc.todolist.GuiListManager;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiIngameMenu;
import net.minecraft.src.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GuiIngameMenu.class,priority = 1500)
public class GuiInGameMenuMixin extends GuiScreen {

    @Inject(method = "initGui", at = @At("RETURN"))
    private void registerMyButton(CallbackInfo ci){
        this.buttonList.add(new GuiButton(14, this.width / 2 - 100, this.height / 4 + 24 + 32,"To Do List Settings"));
    }
    @Inject(method = "actionPerformed", at = @At("RETURN"),cancellable = true)
    private void myButtonclic(GuiButton par1GuiButton, CallbackInfo ci){
        if (par1GuiButton.id == 14){
            this.mc.displayGuiScreen(new GuiListManager(this));
            ci.cancel();
        }
    }

}
