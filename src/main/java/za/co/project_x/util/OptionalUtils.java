package za.co.project_x.util;

import java.util.Optional;

public class OptionalUtils {

    public static <T> T unwrapOptionalOrThrow(Optional<T> optional) {
        return optional.orElseThrow(() -> new IllegalArgumentException("Entity not found"));
    }
}
