package com.zim.terminal.saika.ack;

import java.io.UnsupportedEncodingException;

import com.zim.terminal.utils.FormatUtils;

public class Ack8XXX {
	
	public static byte[] ack8001(String terminal,boolean bool) {
		byte[] _terminal = FormatUtils.strToByte(terminal.substring(2, 22));
		byte sn = FormatUtils.getSN();
        byte[] cmd = new byte[]{(byte) 0x80, 0x01};
        byte[] requestCmd = FormatUtils.strToByte(terminal.substring(28,32));
        byte requestSn = (byte)Integer.parseInt(terminal.substring(22,24),16);
        
        byte[] body;
		if(bool) {
			body = FormatUtils.bytesConcat(FormatUtils.bytesConcat(cmd,requestCmd),new byte[] {requestSn,(byte)0x00});
		}else {
			body = FormatUtils.bytesConcat(FormatUtils.bytesConcat(cmd,requestCmd),new byte[] {requestSn,(byte)0x01});
		}

		int size = body.length;
        byte[] _size;
        if (size <= 0xff) {
            _size = new byte[]{0x00, (byte) size};
        } else {
            _size = new byte[]{(byte) (size / 100), (byte) (size % 100)};
        }
        
        byte[] _attribute = FormatUtils.bytesConcat(
        		FormatUtils.bytesConcat(_terminal, new byte[]{sn}), _size);
        byte[] _message = FormatUtils.bytesConcat(_attribute, body);
        byte xy = FormatUtils.bytesOr(_message);
        byte[] _t1 = FormatUtils.bytesConcat(new byte[]{(byte) 0xaa}, _message); // 起始符
        byte[] _t2 = FormatUtils.bytesConcat(_t1, new byte[]{(byte) xy}); // + 校验
        return FormatUtils.bytesConcat(_t2, new byte[]{(byte) 0xaa}); // + 结束符
        
	}
	
	public static byte[] ack8006(String terminal) {
		StringBuffer tsb = new StringBuffer(terminal);
        while (tsb.length() < 20) {
            tsb.insert(0, '0');
        }
        byte[] _terminal = FormatUtils.strToByte(tsb.toString());
        byte sn = FormatUtils.getSN();
        byte[] body = new byte[]{(byte) 0x80, 0x06};
        int size = body.length;
        byte[] _size;
        if (size <= 0xff) {
            _size = new byte[]{0x00, (byte) size};
        } else {
            _size = new byte[]{(byte) (size / 100), (byte) (size % 100)};
        }
        byte[] _attribute = FormatUtils.bytesConcat(
        		FormatUtils.bytesConcat(_terminal, new byte[]{sn}), _size);
        byte[] _message = FormatUtils.bytesConcat(_attribute, body);
        byte xy = FormatUtils.bytesOr(_message);
        byte[] _t1 = FormatUtils.bytesConcat(new byte[]{(byte) 0xaa}, _message); // 起始符
        byte[] _t2 = FormatUtils.bytesConcat(_t1, new byte[]{(byte) xy}); // + 校验
        return FormatUtils.bytesConcat(_t2, new byte[]{(byte) 0xaa}); // + 结束符
   
	}
	
	public static byte[] ack8008(String terminal,String address) {
		if(address == null || terminal == null) {
			return null;
		}
		
		StringBuffer tsb = new StringBuffer(terminal);
        while (tsb.length() < 20) {
            tsb.insert(0, '0');
        }
        byte[] _terminal = FormatUtils.strToByte(tsb.toString());
        byte sn = FormatUtils.getSN();
        byte[] cmd = new byte[]{(byte) 0x80, 0x08};
        
        byte[] byteAddress = null;
        try {
        	byteAddress = address.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 
        
        byte[] _addSize = {0x00};
        if (byteAddress != null) {
            int addSize = byteAddress.length;
            _addSize = new byte[]{(byte) addSize};
        }
        byte[] allAddress = FormatUtils.bytesConcat(_addSize, byteAddress);
        byte[] body = FormatUtils.bytesConcat(cmd, allAddress);
        
        int size = body.length;
        byte[] _size;
        if (size <= 0xff) {
            _size = new byte[]{0x00, (byte) size};
        } else {
            _size = new byte[]{(byte) (size / 100), (byte) (size % 100)};
        }
        byte[] _attribute = FormatUtils.bytesConcat(
        		FormatUtils.bytesConcat(_terminal, new byte[]{sn}), _size);
        byte[] _message = FormatUtils.bytesConcat(_attribute, body);
        byte xy = FormatUtils.bytesOr(_message);
        byte[] _t1 = FormatUtils.bytesConcat(new byte[]{(byte) 0xaa}, _message); // 起始符
        byte[] _t2 = FormatUtils.bytesConcat(_t1, new byte[]{(byte) xy}); // + 校验
        return FormatUtils.bytesConcat(_t2, new byte[]{(byte) 0xaa}); // + 结束符
       
	}
	
	public static byte[] ack8009(String terminal,boolean bool) {
		StringBuffer tsb = new StringBuffer(terminal);
        while (tsb.length() < 20) {
            tsb.insert(0, '0');
        }
        byte[] _terminal = FormatUtils.strToByte(tsb.toString());
        byte sn = FormatUtils.getSN();
        
        byte[] body;
		if(bool) {
			body = new byte[]{(byte) 0x80, 0x09,0x01};
		}else {
			body = new byte[]{(byte) 0x80, 0x09,0x00};
		}
        int size = body.length;
        byte[] _size;
        if (size <= 0xff) {
            _size = new byte[]{0x00, (byte) size};
        } else {
            _size = new byte[]{(byte) (size / 100), (byte) (size % 100)};
        }
        byte[] _attribute = FormatUtils.bytesConcat(
        		FormatUtils.bytesConcat(_terminal, new byte[]{sn}), _size);
        byte[] _message = FormatUtils.bytesConcat(_attribute, body);
        byte xy = FormatUtils.bytesOr(_message);
        byte[] _t1 = FormatUtils.bytesConcat(new byte[]{(byte) 0xaa}, _message); // 起始符
        byte[] _t2 = FormatUtils.bytesConcat(_t1, new byte[]{(byte) xy}); // + 校验
        return FormatUtils.bytesConcat(_t2, new byte[]{(byte) 0xaa}); // + 结束符
         
	}
	
	public static byte[] ack800A(String terminal,boolean bool) {
		StringBuffer tsb = new StringBuffer(terminal);
        while (tsb.length() < 20) {
            tsb.insert(0, '0');
        }
        byte[] _terminal = FormatUtils.strToByte(tsb.toString());
        byte sn = FormatUtils.getSN();
        
        byte[] body;
		if(bool) {
			body = new byte[]{(byte) 0x80, 0x0A,0x01};
		}else {
			body = new byte[]{(byte) 0x80, 0x0A,0x00};
		}
        int size = body.length;
        byte[] _size;
        if (size <= 0xff) {
            _size = new byte[]{0x00, (byte) size};
        } else {
            _size = new byte[]{(byte) (size / 100), (byte) (size % 100)};
        }
        byte[] _attribute = FormatUtils.bytesConcat(
        		FormatUtils.bytesConcat(_terminal, new byte[]{sn}), _size);
        byte[] _message = FormatUtils.bytesConcat(_attribute, body);
        byte xy = FormatUtils.bytesOr(_message);
        byte[] _t1 = FormatUtils.bytesConcat(new byte[]{(byte) 0xaa}, _message); // 起始符
        byte[] _t2 = FormatUtils.bytesConcat(_t1, new byte[]{(byte) xy}); // + 校验
        return FormatUtils.bytesConcat(_t2, new byte[]{(byte) 0xaa}); // + 结束符
        
	}
	
	public static byte[] ack800C(String terminal,boolean bool) {
		StringBuffer tsb = new StringBuffer(terminal);
        while (tsb.length() < 20) {
            tsb.insert(0, '0');
        }
        byte[] _terminal = FormatUtils.strToByte(tsb.toString());
        byte sn = FormatUtils.getSN();
        
        byte[] body;
		if(bool) {
			body = new byte[]{(byte) 0x80, 0x0C,0x01};
		}else {
			body = new byte[]{(byte) 0x80, 0x0C,0x00};
		}
        int size = body.length;
        byte[] _size;
        if (size <= 0xff) {
            _size = new byte[]{0x00, (byte) size};
        } else {
            _size = new byte[]{(byte) (size / 100), (byte) (size % 100)};
        }
        byte[] _attribute = FormatUtils.bytesConcat(
        		FormatUtils.bytesConcat(_terminal, new byte[]{sn}), _size);
        byte[] _message = FormatUtils.bytesConcat(_attribute, body);
        byte xy = FormatUtils.bytesOr(_message);
        byte[] _t1 = FormatUtils.bytesConcat(new byte[]{(byte) 0xaa}, _message); // 起始符
        byte[] _t2 = FormatUtils.bytesConcat(_t1, new byte[]{(byte) xy}); // + 校验
        return FormatUtils.bytesConcat(_t2, new byte[]{(byte) 0xaa}); // + 结束符
        
	}
	
	public static byte[] ack800D(String terminal) {
		StringBuffer tsb = new StringBuffer(terminal);
        while (tsb.length() < 20) {
            tsb.insert(0, '0');
        }
        byte[] _terminal = FormatUtils.strToByte(tsb.toString());
        byte sn = FormatUtils.getSN();
        byte[] body = new byte[]{(byte) 0x80, 0x0D};
        int size = body.length;
        byte[] _size;
        if (size <= 0xff) {
            _size = new byte[]{0x00, (byte) size};
        } else {
            _size = new byte[]{(byte) (size / 100), (byte) (size % 100)};
        }
        byte[] _attribute = FormatUtils.bytesConcat(
        		FormatUtils.bytesConcat(_terminal, new byte[]{sn}), _size);
        byte[] _message = FormatUtils.bytesConcat(_attribute, body);
        byte xy = FormatUtils.bytesOr(_message);
        byte[] _t1 = FormatUtils.bytesConcat(new byte[]{(byte) 0xaa}, _message); // 起始符
        byte[] _t2 = FormatUtils.bytesConcat(_t1, new byte[]{(byte) xy}); // + 校验
        return FormatUtils.bytesConcat(_t2, new byte[]{(byte) 0xaa}); // + 结束符
        
	}
	
	public static byte[] ack800E(String terminal,String pass) {
		if(pass == null || terminal == null) {
			return null;
		}
		
		StringBuffer tsb = new StringBuffer(terminal);
        while (tsb.length() < 20) {
            tsb.insert(0, '0');
        }
        byte[] _terminal = FormatUtils.strToByte(tsb.toString());
        byte sn = FormatUtils.getSN();
        byte[] cmd = new byte[]{(byte) 0x80, 0x0E};
        
        byte[] byteAddress = null;
        try {
        	byteAddress = pass.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 
        
        byte[] _addSize = {0x00};
        if (byteAddress != null) {
            int addSize = byteAddress.length;
            _addSize = new byte[]{(byte) addSize};
        }
        byte[] allAddress = FormatUtils.bytesConcat(_addSize, byteAddress);
        byte[] body = FormatUtils.bytesConcat(cmd, allAddress);
        
        int size = body.length;
        byte[] _size;
        if (size <= 0xff) {
            _size = new byte[]{0x00, (byte) size};
        } else {
            _size = new byte[]{(byte) (size / 100), (byte) (size % 100)};
        }
        byte[] _attribute = FormatUtils.bytesConcat(
        		FormatUtils.bytesConcat(_terminal, new byte[]{sn}), _size);
        byte[] _message = FormatUtils.bytesConcat(_attribute, body);
        byte xy = FormatUtils.bytesOr(_message);
        byte[] _t1 = FormatUtils.bytesConcat(new byte[]{(byte) 0xaa}, _message); // 起始符
        byte[] _t2 = FormatUtils.bytesConcat(_t1, new byte[]{(byte) xy}); // + 校验
        return FormatUtils.bytesConcat(_t2, new byte[]{(byte) 0xaa}); // + 结束符
        
	}
	
	public static byte[] ack800F(String terminal) {
		StringBuffer tsb = new StringBuffer(terminal);
        while (tsb.length() < 20) {
            tsb.insert(0, '0');
        }
        byte[] _terminal = FormatUtils.strToByte(tsb.toString());
        byte sn = FormatUtils.getSN();
        byte[] body = new byte[]{(byte) 0x80, 0x0F};
        int size = body.length;
        byte[] _size;
        if (size <= 0xff) {
            _size = new byte[]{0x00, (byte) size};
        } else {
            _size = new byte[]{(byte) (size / 100), (byte) (size % 100)};
        }
        byte[] _attribute = FormatUtils.bytesConcat(
        		FormatUtils.bytesConcat(_terminal, new byte[]{sn}), _size);
        byte[] _message = FormatUtils.bytesConcat(_attribute, body);
        byte xy = FormatUtils.bytesOr(_message);
        byte[] _t1 = FormatUtils.bytesConcat(new byte[]{(byte) 0xaa}, _message); // 起始符
        byte[] _t2 = FormatUtils.bytesConcat(_t1, new byte[]{(byte) xy}); // + 校验
        return FormatUtils.bytesConcat(_t2, new byte[]{(byte) 0xaa}); // + 结束符
        
	}
	
	public static byte[] ack8010(String terminal,int type) {
		StringBuffer tsb = new StringBuffer(terminal);
        while (tsb.length() < 20) {
            tsb.insert(0, '0');
        }
        byte[] _terminal = FormatUtils.strToByte(tsb.toString());
        byte sn = FormatUtils.getSN();
        
        byte[] body;
        switch (type) {
		case 1:
			body = new byte[]{(byte) 0x80, 0x10,0x01};
			break;
		case 2:
			body = new byte[]{(byte) 0x80, 0x10,0x02};
			break;
		case 9:
			body = new byte[]{(byte) 0x80, 0x10,0x09};
			break;
		case 81:
		    body = new byte[]{(byte) 0x80, 0x10,(byte) 0x81};
		    break;
		case 82:
		    body = new byte[]{(byte) 0x80, 0x10,(byte) 0x82};
            break;
		case 83:
            body = new byte[]{(byte) 0x80, 0x10,(byte) 0x83};
            break;
        case 84:
            body = new byte[]{(byte) 0x80, 0x10,(byte) 0x84};
            break;
        case 85:
            body = new byte[]{(byte) 0x80, 0x10,(byte) 0x85};
            break;
        case 86:
            body = new byte[]{(byte) 0x80, 0x10,(byte) 0x86};
            break;
        case 87:
            body = new byte[]{(byte) 0x80, 0x10,(byte) 0x87};
            break;
        case 88:
            body = new byte[]{(byte) 0x80, 0x10,(byte) 0x88};
            break;
		default:
			body = new byte[]{(byte) 0x80, 0x10,0x00};
			break;
		}
        
        int size = body.length;
        byte[] _size;
        if (size <= 0xff) {
            _size = new byte[]{0x00, (byte) size};
        } else {
            _size = new byte[]{(byte) (size / 100), (byte) (size % 100)};
        }
        byte[] _attribute = FormatUtils.bytesConcat(
        		FormatUtils.bytesConcat(_terminal, new byte[]{sn}), _size);
        byte[] _message = FormatUtils.bytesConcat(_attribute, body);
        byte xy = FormatUtils.bytesOr(_message);
        byte[] _t1 = FormatUtils.bytesConcat(new byte[]{(byte) 0xaa}, _message); // 起始符
        byte[] _t2 = FormatUtils.bytesConcat(_t1, new byte[]{(byte) xy}); // + 校验
        return FormatUtils.bytesConcat(_t2, new byte[]{(byte) 0xaa}); // + 结束符
       
	}
	
	public static byte[] ack8011(String terminal,String productKey,String secret) {
		StringBuffer tsb = new StringBuffer(terminal);
        while (tsb.length() < 20) {
            tsb.insert(0, '0');
        }
        byte[] _terminal = FormatUtils.strToByte(tsb.toString());
        byte sn = FormatUtils.getSN();
        byte[] cmd = new byte[]{(byte) 0x80, 0x11};
        
        StringBuffer secretStr = new StringBuffer(productKey);
        secretStr.append(secret);
        
        byte[] secretByte = null;
        try {
        	secretByte = secretStr.toString().getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
        
        byte[] body = FormatUtils.bytesConcat(cmd, secretByte);
        
        int size = body.length;
        
        byte[] _size;
        if (size <= 0xff) {
            _size = new byte[]{0x00, (byte) size};
        } else {
            _size = new byte[]{(byte) (size / 100), (byte) (size % 100)};
        }
        byte[] _attribute = FormatUtils.bytesConcat(
        		FormatUtils.bytesConcat(_terminal, new byte[]{sn}), _size);
        byte[] _message = FormatUtils.bytesConcat(_attribute, body);
        byte xy = FormatUtils.bytesOr(_message);
        byte[] _t1 = FormatUtils.bytesConcat(new byte[]{(byte) 0xaa}, _message); // 起始符
        byte[] _t2 = FormatUtils.bytesConcat(_t1, new byte[]{(byte) xy}); // + 校验
        return FormatUtils.bytesConcat(_t2, new byte[]{(byte) 0xaa}); // + 结束符
		
	}

}
