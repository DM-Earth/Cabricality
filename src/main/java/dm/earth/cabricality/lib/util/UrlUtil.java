package dm.earth.cabricality.lib.util;

public class UrlUtil {
	public static String generateCurseForgeModFileUrl(String slug, long version) {
		return String.format(
				"https://www.curseforge.com/minecraft/mc-mods/%s/download/%d/file",
				slug, version
		);
	}
}
