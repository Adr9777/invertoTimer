package top.ourisland.invertotimer.showcase;

import com.velocitypowered.api.proxy.Player;
import top.ourisland.invertotimer.runtime.I18n;
import top.ourisland.invertotimer.runtime.RuntimeContext;

import java.util.Objects;
import java.util.function.Supplier;

public class ActionbarShowcase implements Showcase {
    private final RuntimeContext ctx;
    private final Supplier<Object> textSupplier;

    public ActionbarShowcase(RuntimeContext ctx, Supplier<Object> textSupplier) {
        this.ctx = Objects.requireNonNull(ctx);
        this.textSupplier = Objects.requireNonNull(textSupplier);
    }

    @Override
    public String name() {
        return "actionbar";
    }

    @Override
    public String description() {
        return I18n.langStrNP("itimer.showcase.actionbar.desc");
    }

    @Override
    public void show() {
        final String raw = String.valueOf(textSupplier.get());
        for (Player p : ctx.players()) {
            if (!ctx.allowed(p)) continue;
            p.sendActionBar(ctx.render(raw));
        }
    }
}
