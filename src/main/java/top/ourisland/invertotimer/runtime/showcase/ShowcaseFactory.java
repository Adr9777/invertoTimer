package top.ourisland.invertotimer.runtime.showcase;

import top.ourisland.invertotimer.config.model.ShowcaseConfig;
import top.ourisland.invertotimer.runtime.RuntimeContext;
import top.ourisland.invertotimer.showcase.*;

import java.util.Locale;
import java.util.function.Supplier;

public final class ShowcaseFactory {
    private ShowcaseFactory() {
    }

    public static Showcase create(
            String key,
            ShowcaseConfig sc,
            RuntimeContext ctx,
            Supplier<Object> textSupplier,
            Supplier<Float> progressSupplier
    ) {
        return switch (key.toLowerCase(Locale.ROOT)) {
            case "bossbar" -> new BossbarShowcase(ctx, textSupplier, progressSupplier, sc.color());
            case "title" -> new TitleShowcase(ctx, textSupplier);
            case "actionbar" -> new ActionbarShowcase(ctx, textSupplier);
            case "text" -> new TextShowcase(ctx, textSupplier);
            default -> null;
        };
    }
}
