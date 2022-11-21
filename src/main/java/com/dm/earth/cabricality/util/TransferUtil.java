package com.dm.earth.cabricality.util;

import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;

@SuppressWarnings("UnstableApiUsage")
public class TransferUtil {
	public static Transaction getTransaction() {
		return io.github.fabricators_of_create.porting_lib.transfer.TransferUtil.getTransaction();
	}
}
