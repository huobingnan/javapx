package ano.nunu.jvmabi.attribute;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BootstrapMethod {
    private short methodRef;
    private short[] args; // args type which will depict in constant pool.
}
