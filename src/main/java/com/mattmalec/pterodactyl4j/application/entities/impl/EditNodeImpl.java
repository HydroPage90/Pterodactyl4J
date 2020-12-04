package com.mattmalec.pterodactyl4j.application.entities.impl;

import com.mattmalec.pterodactyl4j.PteroActionImpl;
import com.mattmalec.pterodactyl4j.application.entities.Node;
import com.mattmalec.pterodactyl4j.application.managers.abstracts.AbstractNodeAction;
import com.mattmalec.pterodactyl4j.entities.PteroAction;
import com.mattmalec.pterodactyl4j.requests.Route;
import org.json.JSONObject;

public class EditNodeImpl extends AbstractNodeAction {

	private final Node node;

	EditNodeImpl(PteroApplicationImpl impl, Node node) {
		super(impl);
		this.node = node;
	}

	@Override
	public PteroAction<Node> build() {
		return PteroActionImpl.onExecute(() -> {
			JSONObject json = new JSONObject();
			if (name == null)
				json.put("name", node.getName());
			else
				json.put("name", name);
			if (location == null)
				json.put("location_id", node.getLocation().retrieve().execute().getId());
			else
				json.put("location_id", location.getId());
			if (isPublic == null)
					json.put("public", node.isPublic() ? "1" : "0");
				else
					json.put("public", isPublic ? "1" : "0");
				if(fqdn == null)
					json.put("fqdn", node.getFQDN());
				else
					json.put("fqdn", fqdn);
				if(secure == null)
					json.put("scheme", node.getScheme());
				else
					json.put("scheme", secure ? "https" : "http");
				if(isBehindProxy == null)
					json.put("behind_proxy", node.isBehindProxy() ? "1" : "0");
				else
					json.put("behind_proxy", isBehindProxy ? "1" : "0");
				if(daemonBase == null)
					json.put("daemon_base", node.getDaemonBase());
				else
					json.put("daemon_base", daemonBase);
				if(memory == null)
					json.put("memory", node.getMemoryLong());
				else
					json.put("memory", Long.parseLong(memory));
				if(memoryOverallocate == null)
					json.put("memory_overallocate", node.getMemoryOverallocateLong());
				else
					json.put("memory_overallocate", Long.parseLong(memoryOverallocate));
				if(diskSpace == null)
					json.put("disk", node.getDiskLong());
				else
					json.put("disk", Long.parseLong(diskSpace));
				if(diskSpaceOverallocate == null)
					json.put("disk_overallocate", node.getDiskOverallocateLong());
				else
					json.put("disk_overallocate", Long.parseLong(diskSpaceOverallocate));
				if(daemonListenPort == null)
					json.put("daemon_listen", node.getDaemonListenPort());
				else
					json.put("daemon_listen", daemonListenPort);
				if(daemonSFTPPort == null)
					json.put("daemon_sftp", node.getDaemonSFTPPort());
				else
					json.put("daemon_sftp", daemonSFTPPort);
			if (throttle == null)
				json.put("throttle", new JSONObject().put("enabled", false));
			else
				json.put("throttle", new JSONObject().put("enabled", throttle));
			Route.CompiledRoute route = Route.Nodes.EDIT_NODE.compile(node.getId()).withJSONdata(json);
			JSONObject jsonObject = requester.request(route).toJSONObject();
			return new NodeImpl(jsonObject, impl);
		});
	}
}
