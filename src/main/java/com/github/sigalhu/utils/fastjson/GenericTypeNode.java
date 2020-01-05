package com.github.sigalhu.utils.fastjson;

import com.github.sigalhu.utils.ArrayUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * @author huxujun
 * @date 2019/12/14
 */
@Getter
@AllArgsConstructor
public class GenericTypeNode {
    private Type type;
    private List<GenericTypeNode> nextNodes;

    public GenericTypeNode(Type type, GenericTypeNode... nextNodes) {
        this.type = type;
        if (ArrayUtils.isNotEmpty(nextNodes)) {
            this.nextNodes = Arrays.asList(nextNodes);
        }
    }
}
