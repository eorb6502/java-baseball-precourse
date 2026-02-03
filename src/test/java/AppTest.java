import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;

class AppTest {
	@Test
	void mainMethodHasExpectedSignature() throws Exception {
		Method main = App.class.getDeclaredMethod("main", String[].class);

		assertThat(main.getReturnType()).isEqualTo(void.class);
		assertThat(Modifier.isPublic(main.getModifiers())).isTrue();
		assertThat(Modifier.isStatic(main.getModifiers())).isTrue();
	}
}
