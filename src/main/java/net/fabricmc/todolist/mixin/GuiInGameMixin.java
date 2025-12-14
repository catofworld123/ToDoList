package net.fabricmc.todolist.mixin;


import net.fabricmc.todolist.ListManager;
import net.fabricmc.todolist.StringStorage;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.GuiIngame;
import net.minecraft.src.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Mixin(GuiIngame.class)
public class GuiInGameMixin {
    @Shadow @Final private Minecraft mc;

    @Inject(method = "renderGameOverlay", at = @At("HEAD"), cancellable = true)

    public void RenderCounters(float par1, boolean par2, int par3, int par4, CallbackInfo ci){

        if (this.mc.gameSettings.debugScreenState == 0) {

            ListManager manager = new ListManager();

            List<String> strings = manager.getStrings();

            if (manager.getoverlayConfig()) {

                FontRenderer renderer = this.mc.fontRenderer;
                this.mc.entityRenderer.setupOverlayRendering();

                if (manager.getMode().equals("To Do List")){
                    int y = 1;
                    int x = 1;
                    for (int i = 0;i < manager.lineNum;i++){

                        String text = strings.get(i);

                        renderer.drawString(text, x - 1, y+10*i, 0);
                        renderer.drawString(text, x + 1, y+10*i, 0);
                        renderer.drawString(text, x, y+10*i + 1, 0);
                        renderer.drawString(text, x, y+10*i - 1, 0);
                        renderer.drawString(text, x, y+10*i, 0xFFFFFF);
                    }

                }
                else{
                    int y = 1;
                    int x = 1;
                    String text = "Goal: " + manager.goalString.trim();
                    renderer.drawString(text, x - 1, y, 0);
                    renderer.drawString(text, x + 1, y, 0);
                    renderer.drawString(text, x, y + 1, 0);
                    renderer.drawString(text, x, y - 1, 0);
                    renderer.drawString(text, x, y, 0xFFFFFF);
                }
            }


        }
    }
}
