package top.ourisland.invertotimer.config.model;

import top.ourisland.invertotimer.runtime.timer.TimeUtil;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record ShowcaseConfig(
        boolean enabled,
        Duration startAt,
        Duration interval,
        Object text,
        String color,
        After after
) {
    public static ShowcaseConfig fromYaml(final Object obj) {
        if (!(obj instanceof Map<?, ?> m)) return null;

        boolean enabled = true;
        Object enabledRaw = m.get("enabled");
        if (enabledRaw instanceof Boolean b) enabled = b;
        else if (enabledRaw != null) enabled = Boolean.parseBoolean(String.valueOf(enabledRaw));

        Duration startAt = TimeUtil.parseDurationLoose(m.get("start-at"));
        Duration interval = TimeUtil.parseDurationLoose(m.get("interval"));

        Object text = m.get("text");

        if (text == null && (m.containsKey("title") || m.containsKey("subtitle"))) {
            String t = m.get("title") == null ? "" : String.valueOf(m.get("title"));
            String s = m.get("subtitle") == null ? "" : String.valueOf(m.get("subtitle"));
            List<String> list = new ArrayList<>();
            list.add(t);
            list.add(s);
            text = list;
        }

        String color = m.get("color") == null ? null : String.valueOf(m.get("color"));

        After after = null;
        Object afterObj = m.get("after");
        if (afterObj instanceof Map<?, ?> am) {
            Object at = am.get("text");
            Duration dur = TimeUtil.parseDurationLoose(am.get("duration"));
            if (dur == null) dur = Duration.ZERO;
            if (at != null || !dur.isZero()) after = new After(at, dur);
        }

        return new ShowcaseConfig(enabled, startAt, interval, text, color, after);
    }

    public record After(Object text, Duration duration) {
    }
}
