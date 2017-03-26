# mysensors-controller-java
Simple **headless** mysensors controller library.

## Purpose

This is personal project that I wanted to share.
The API isn't that clean, but it's workable.
It was made to create a quick prototype with the help of an RaspberryPI & MySensors.

Headless mean there's no UI, it's really just a library, some kind of a wrapper.

## Installation

## Usage

Instantiate a new controller. You can instantiate multiple gateway if needed.

```java
Controller controller = new Controller('/dev/ttyGatewaySerial'); // change this according to your need
controller.start()
```
Stop the watching of the `tty`. No events will be generated.

```java
controller.stop();
```

To listen to events first you need to implement the `NodeListener`. Then you need to register that listener to the controller.

```java
public class MyClass implements NodeListener {
    
    // New data from sensor is available
    public void sensorDataReceived(Node sensorNode, TypeReqSet typeReq, float value) {
        // ... my code
    }
}
```

```java
MyClass myClass = new MyClass();
controller.addNodeListener(myClass);
```

## Limitations

**Auto ID**

At this stage, auto setting of ID is not implemented.

**Threading**

Performance can be an issue, as there's no threading is involved.
That library is event based, when new data arrive then an event is generated.
If there's a lot of activity this can cause performance issue.

You'd be fine if you have a simple setup. If you have a setup with hundreds of nodes, then it is clearly an issue.

## Links

- [https://www.mysensors.org/](https://www.mysensors.org/)