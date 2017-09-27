namespace java com.iusworks.jaguar.thrift
namespace php com.iusworks.jaguar

enum DeviceType {
    iOS = 1,
    Android = 2
}

enum Environment {
    Dev = 1,
    Prod = 2
}

struct DevicePlatformVoucher {
    1: required string platform;    //平台
    2: required string voucher;     //平台对应的ID
    3: required i16 state;
}

struct Device {
    1: required DeviceType type;
    2: required string uid;
    3: required string voucher;   
    4: optional set<string> tags;
    5: optional set<string> cares;   // 关心推送什么类型的数据
    6: required i16 state;
    7: optional string deviceId;    //设备ID
    8: optional set<DevicePlatformVoucher> dpv;
    9: optional map<string,string> deviceInfo;
}


struct Notification {
    1: required Environment env;
    2: optional string uid;
    3: optional set<string> tags;
    4: required string sound;
    5: optional string title;
    6: required string alert;
    7: optional string category;
    8: optional string action; // Android直接是action, iOS
    9: optional map<string,string> ext;
    10: optional string storaged;
    11: optional i32 badge;
    12: optional i32 eventTime;  //事件发生事件
}


struct NotificationReport {
    1: required string notificationId;
    2: required i32 recvTime;
    3: required string channel;
    4: optional string deviceName;
}

struct DeviceRequest {
    1: required i16 systemId;
    2: required i32 time;
    3: required string signature;
    4: required Device device;
}


struct DevicePlatformVoucherRequest {
    1: required i16 systemId;
    2: required i32 time;
    3: required string signature;
    4: required string uid;
    5: required DevicePlatformVoucher dpv;
}

struct NotificationRequest {
    1: required i16 systemId;
    2: required i32 time;
    3: required string signature;
    4: required Notification notification;
}

struct QueryNotificationRequest{
    1: required i16 systemId;
    2: required i32 time;
    3: required string signature;
    4: required string uid;
    5: required i32 start;
}

struct NotificationReportRequest {
    1: required i16 systemId;
    2: required i32 time;
    3: required string signature;
    4: required NotificationReport report;
}

struct NotificationHistory {
    1: required string mid;
    2: required string uid;
    3: required i32  datetime;
    4: required string action;
    5: required string title;
    6: required string alert;
    7: optional string storaged;
}

exception JaguarException {
    1: required i32 code;
    2: required string message;
}

service JaguarService {
    bool device(1:DeviceRequest deviceRequest) throws (1:JaguarException ex);
    bool devicePlatformVoucher(1:DevicePlatformVoucherRequest dpvRequest) throws (1:JaguarException ex);
    bool push(1:NotificationRequest notificationRequest) throws (1:JaguarException ex);
    list<NotificationHistory> notificationHistory(1:QueryNotificationRequest queryNotificationRequest) throws (1:JaguarException ex);
    bool notificationReport(1: NotificationReportRequest notificationReportRequest) throws (1:JaguarException ex);
}


