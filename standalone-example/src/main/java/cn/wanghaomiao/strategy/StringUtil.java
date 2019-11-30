package cn.wanghaomiao.strategy;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLStreamHandler;

public class StringUtil {

//    public void checkIsUrl(URL context, String spec, URLStreamHandler handler)
//            throws MalformedURLException
//    {
//        String protocol;
//        String original = spec;
//        int i, limit, c;
//        int start = 0;
//        String newProtocol = null;
//        boolean aRef=false;
//        boolean isRelative = false;
//
//        // Check for permission to specify a handler
//        if (handler != null) {
//            SecurityManager sm = System.getSecurityManager();
//            if (sm != null) {
//              //  checkSpecifyHandler(sm);
//            }
//        }
//
//        try {
//            limit = spec.length();
//            while ((limit > 0) && (spec.charAt(limit - 1) <= ' ')) {
//                limit--;        //eliminate trailing whitespace
//            }
//            while ((start < limit) && (spec.charAt(start) <= ' ')) {
//                start++;        // eliminate leading whitespace
//            }
//
//            if (spec.regionMatches(true, start, "url:", 0, 4)) {
//                start += 4;
//            }
//            if (start < spec.length() && spec.charAt(start) == '#') {
//                /* we're assuming this is a ref relative to the context URL.
//                 * This means protocols cannot start w/ '#', but we must parse
//                 * ref URL's like: "hello:there" w/ a ':' in them.
//                 */
//                aRef=true;
//            }
//            for (i = start ; !aRef && (i < limit) &&
//                    ((c = spec.charAt(i)) != '/') ; i++) {
//                if (c == ':') {
//
//                    String s = spec.substring(start, i).toLowerCase();
//                    if (isValidProtocol(s)) {
//                        newProtocol = s;
//                        start = i + 1;
//                    }
//                    break;
//                }
//            }
//
//            // Only use our context if the protocols match.
//            protocol = newProtocol;
//            if ((context != null) && ((newProtocol == null) ||
//                    newProtocol.equalsIgnoreCase(context.protocol))) {
//                // inherit the protocol handler from the context
//                // if not specified to the constructor
//                if (handler == null) {
//                    handler = context.handler;
//                }
//
//                // If the context is a hierarchical URL scheme and the spec
//                // contains a matching scheme then maintain backwards
//                // compatibility and treat it as if the spec didn't contain
//                // the scheme; see 5.2.3 of RFC2396
//                if (context.path != null && context.path.startsWith("/"))
//                    newProtocol = null;
//
//                if (newProtocol == null) {
//                    protocol = context.protocol;
//                    authority = context.authority;
//                    userInfo = context.userInfo;
//                    host = context.host;
//                    port = context.port;
//                    file = context.file;
//                    path = context.path;
//                    isRelative = true;
//                }
//            }
//
//            if (protocol == null) {
//                throw new MalformedURLException("no protocol: "+original);
//            }
//
//            // Get the protocol handler if not specified or the protocol
//            // of the context could not be used
//            if (handler == null &&
//                    (handler = getURLStreamHandler(protocol)) == null) {
//                throw new MalformedURLException("unknown protocol: "+protocol);
//            }
//
//            this.handler = handler;
//
//            i = spec.indexOf('#', start);
//            if (i >= 0) {
//                ref = spec.substring(i + 1, limit);
//                limit = i;
//            }
//
//            /*
//             * Handle special case inheritance of query and fragment
//             * implied by RFC2396 section 5.2.2.
//             */
//            if (isRelative && start == limit) {
//                query = context.query;
//                if (ref == null) {
//                    ref = context.ref;
//                }
//            }
//
//            handler.parseURL(this, spec, start, limit);
//
//        } catch(MalformedURLException e) {
//            throw e;
//        } catch(Exception e) {
//            MalformedURLException exception = new MalformedURLException(e.getMessage());
//            exception.initCause(e);
//            throw exception;
//        }
//    }

    /*
     * Returns true if specified string is a valid protocol name.
     */
    private boolean isValidProtocol(String protocol) {
        int len = protocol.length();
        if (len < 1)
            return false;
        char c = protocol.charAt(0);
        if (!Character.isLetter(c))
            return false;
        for (int i = 1; i < len; i++) {
            c = protocol.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != '.' && c != '+' &&
                    c != '-') {
                return false;
            }
        }
        return true;
    }
}
