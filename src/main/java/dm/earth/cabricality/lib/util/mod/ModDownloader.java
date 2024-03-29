package dm.earth.cabricality.lib.util.mod;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dm.earth.cabricality.lib.util.log.CabfLogger;

@Deprecated
public class ModDownloader {
	private final Map<String, String> urls;
	private final ArrayList<String> succeed = new ArrayList<>();

	public ModDownloader(@NotNull Map<String, String> urls) {
		this.urls = urls;
	}

	@Nullable
	public ArrayList<String> download() {
		urls.forEach((key, value) -> {
			try {
				CabfLogger.info("Downloading " + key + "...");
				DownloadThread thread = new DownloadThread(key, new URL(value));
				while (thread.isAlive()) {
					CabfLogger.debug("Downloading " + key + "...");
				}
				succeed.addAll(thread.getSucceed());
			} catch (MalformedURLException malformedURLException) {
				CabfLogger.error("Failed to download mod " + key + ", url is malformed!");
				CabfLogger.debug("Failed to download mod " + key + ", url is malformed!",
						malformedURLException);
			}
		});
		return succeed.isEmpty() ? null : succeed;
	}

	static class DownloadThread extends Thread {
		private final String mod;
		private final URL url;
		private final ArrayList<String> succeed = new ArrayList<>();

		public DownloadThread(@NotNull String mod, @NotNull URL url) {
			this.mod = mod;
			this.url = url;
			this.start();
		}

		public ArrayList<String> getSucceed() {
			return succeed;
		}

		@Override
		public void start() {
			try {
				File file = FabricLoader.getInstance().getGameDir().resolve("mods").resolve(mod + ".jar").toFile();
				if (!file.getParentFile().exists())
					file.getParentFile().mkdirs();

				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setConnectTimeout(10 * 1000);
				connection.setRequestProperty("User-Agent",
						"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

				InputStream inputStream = connection.getInputStream();
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				fileOutputStream.write(readInputStream(inputStream));

				fileOutputStream.close();
				inputStream.close();
				succeed.add(mod);
			} catch (IOException ioException) {
				CabfLogger.error("Failed to download mod " + mod + ", connection failed!");
				CabfLogger.debug("Failed to download mod " + mod + ", connection failed!",
						ioException);
			}
		}

		private byte[] readInputStream(InputStream inputStream) throws IOException {
			byte[] buffer = new byte[1024];
			int len = 0;
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			while ((len = inputStream.read(buffer)) != -1)
				byteArrayOutputStream.write(buffer, 0, len);
			byteArrayOutputStream.close();
			return byteArrayOutputStream.toByteArray();
		}
	}
}
