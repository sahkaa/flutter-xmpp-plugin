package org.xrstudio.xmpp.flutter_xmpp.managers;

import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smackx.mam.MamManager;
import org.jivesoftware.smackx.mam.element.MamElements;
import org.jivesoftware.smackx.push_notifications.PushNotificationsManager;
import org.jxmpp.jid.Jid;
import org.jxmpp.jid.impl.JidCreate;
import org.xrstudio.xmpp.flutter_xmpp.Connection.FlutterXmppConnection;
import org.xrstudio.xmpp.flutter_xmpp.Utils.Utils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PushNotificationManager {

    public static void enablePushNotification(String deviceId, String service, String priority, String node, String pushjid, String topic, String silent) {

        XMPPTCPConnection connection = FlutterXmppConnection.getConnection();

        if (connection.isAuthenticated()) {
            try {

                HashMap<String, String> publishOptions = new HashMap<>();
                publishOptions.put("service", service);
                publishOptions.put("device_id", deviceId);
                if (priority != null) {
                    publishOptions.put("priotiy", service);
                }
                if (topic != null) {
                    publishOptions.put("topic", topic);
                }
                if (silent != null) {
                    publishOptions.put("silent", silent);
                }

                PushNotificationsManager pushNotificationsManager = PushNotificationsManager.getInstanceFor(connection);
                pushNotificationsManager.enable(JidCreate.domainBareFrom(pushjid), node, publishOptions);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static void disalbePushNotification(String node, String pushjid) {
         XMPPTCPConnection connection = FlutterXmppConnection.getConnection();
        if (connection.isAuthenticated()) {
            try {
                PushNotificationsManager pushNotificationsManager = PushNotificationsManager.getInstanceFor(connection);
                pushNotificationsManager.disable(JidCreate.domainBareFrom(pushjid), node);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
