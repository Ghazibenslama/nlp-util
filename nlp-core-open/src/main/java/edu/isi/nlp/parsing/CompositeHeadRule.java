package edu.isi.nlp.parsing;

import com.google.common.annotations.Beta;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import edu.isi.nlp.ConstituentNode;

@Beta
final class CompositeHeadRule<NodeT extends ConstituentNode<NodeT, ?>> implements HeadRule<NodeT> {

  private final ImmutableList<HeadRule<NodeT>> rulesInOrder;

  private CompositeHeadRule(final Iterable<HeadRule<NodeT>> rulesInOrder) {
    this.rulesInOrder = ImmutableList.copyOf(rulesInOrder);
  }

  public static <NodeT extends ConstituentNode<NodeT, ?>> CompositeHeadRule<NodeT> create(
      final Iterable<HeadRule<NodeT>> rulesInOrder) {
    return new CompositeHeadRule<NodeT>(rulesInOrder);
  }

  @SafeVarargs
  public static <NodeT extends ConstituentNode<NodeT, ?>> CompositeHeadRule<NodeT> create(
      final HeadRule<NodeT>... rulesInOrder) {
    return new CompositeHeadRule<NodeT>(ImmutableList.copyOf(rulesInOrder));
  }

  @Override
  public Optional<NodeT> matchForChildren(final Iterable<NodeT> children) {
    for (final HeadRule<NodeT> r : rulesInOrder) {
      final Optional<NodeT> ret = r.matchForChildren(children);
      if (ret.isPresent()) {
        return ret;
      }
    }
    return Optional.absent();
  }
}
