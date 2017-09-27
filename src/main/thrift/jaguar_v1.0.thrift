namespace java com.iusworks.jaguar.thrift
namespace php com.iusworks.jaguar

enum DeviceType {
    iOS = 1,
    Android = 2,
    WebChrome = 10,
    WebFirefox = 11,
    WebSafari = 12,
    WebEDGE = 13,

    MobileChrome = 20,
    MobileFirefox = 21,
    MobileSafari = 22,
    MobileEDGE = 23,

    Other = 99
}

enum Environment {
    Dev = 1,
    Prod = 2,
    Test = 3
}

enum DeviceTokenRegisteType {
    Auto = 1,
    Manual = 2,
    Schedule = 3
}

struct DeviceToken {
    1: required string platform;
    2: required i32 registeTime;
    3: required i32 registeType;
    4: optional map<string,string> ext;
}

struct Device {
    1: required DeviceType type;
    2: required string uid;
    3: required string voucher;
    4: optional set<string> tags;
    5: optional set<string> cares;   // 关心推送什么类型的数据
    6: required i16 state;
    7: optional map<string, DeviceToken> tokens;
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

struct DeviceRequest {
    1: required i16 systemId;
    2: required i32 time;
    3: required string signature;
    4: required Device device;
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
    bool push(1:NotificationRequest notificationRequest) throws (1:JaguarException ex);
    list<NotificationHistory> notificationHistory(1:QueryNotificationRequest queryNotificationRequest) throws (1:JaguarException ex);
}


