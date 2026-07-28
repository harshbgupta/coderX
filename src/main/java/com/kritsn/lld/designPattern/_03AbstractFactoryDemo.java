package com.kritsn.lld.designPattern;


import java.util.Map;
import java.util.function.Supplier;

//Step 1: Define the AbstractProducts
//Rule of thumb: one interface per "thing that must vary together." Count them — this count becomes your factory interface's method count in Step 3.
interface Button{
    void render();
}
interface Checkbox{
    void render();
}

//Step 2: List your ConcreteProducts  by their families
class WindowsButton implements Button {
    public void render() { System.out.println("Rendering Windows-style button"); }
}
class WindowsCheckbox implements Checkbox {
    public void render() { System.out.println("Rendering Windows-style checkbox"); }
}
class MacButton implements Button {
    public void render() { System.out.println("Rendering Mac-style button"); }
}
class MacCheckbox implements Checkbox {
    public void render() { System.out.println("Rendering Mac-style checkbox"); }
}
class LinuxButton implements Button {
    public void render() { System.out.println("Rendering Linux-style (GTK) button"); }
}
class LinuxCheckbox implements Checkbox {
    public void render() { System.out.println("Rendering Linux-style (GTK) checkbox"); }
}

//Step 3: Define the AbstractFactory interface — one method per column from Step 2
//Rule of thumb: number of methods = number of columns in Step 2's grid. (Compare to Factory Method, where the Creator had exactly ONE abstract method — here it's N, one per family member.)
interface UIFactory{
    Button createButton();
    Checkbox createCheckbox();
}

//Step 4: Define one ConcreteFactory per row from Step 2
//Critical design rule: every method inside one ConcreteFactory must return products from the same row. If WindowsUIFactory.createCheckbox() ever returned a MacCheckbox, the entire pattern's guarantee is broken — this is the one thing to double check when reviewing your own code.
class WindowsUIFactory implements UIFactory {
    public Button createButton() { return new WindowsButton(); }
    public Checkbox createCheckbox() { return new WindowsCheckbox(); }
}
class MacUIFactory implements UIFactory {
    public Button createButton() { return new MacButton(); }
    public Checkbox createCheckbox() { return new MacCheckbox(); }
}
class LinuxUIFactory implements UIFactory {
    public Button createButton() { return new LinuxButton(); }
    public Checkbox createCheckbox() { return new LinuxCheckbox(); }
}
//Step 5: Client only ever talks to AbstractFactory + AbstractProducts — never concrete classes
class ScreenRenderer {
    private final Button button;
    private final Checkbox checkbox;

    ScreenRenderer(UIFactory factory) {          // only knows UIFactory (abstraction)
        this.button = factory.createButton();     // only knows Button (abstraction)
        this.checkbox = factory.createCheckbox(); // only knows Checkbox (abstraction)
    }

    void render() {
        button.render();
        checkbox.render();
    }
}

//Step 6: factory creation based on type
class UIFactoryRegistry {
    private static final Map<String, Supplier<UIFactory>> registry = Map.of(
            "windows", WindowsUIFactory::new,
            "mac", MacUIFactory::new,
            "linux", LinuxUIFactory::new
    );

    static UIFactory get(String osName) {
        Supplier<UIFactory> supplier = registry.get(osName.toLowerCase());
        if (supplier == null) {
            throw new IllegalArgumentException("No factory registered for: " + osName);
        }
        return supplier.get();
    }
}

//Step t: The single decision point — where the concrete factory is chosen
public class _03AbstractFactoryDemo {
    public static void main(String[] args) {
        String osName = System.getProperty("os.name");
        UIFactory factory = UIFactoryRegistry.get(osName);

        ScreenRenderer renderer = new ScreenRenderer(factory);
        renderer.render();
    }
}